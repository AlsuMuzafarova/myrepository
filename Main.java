package controlWork.v15;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final String LOG_FILE_TEMPLATE = "v%d.log";
    private static final String OUTPUT_FILE_TEMPLATE = "v%d.txt";
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        int variantNumber = 15;

        // Создать лог-файл
        BufferedWriter logWriter = createLogFile(variantNumber);

        // Создать список путей к файлам
        File folder = new File("C:\\Users\\alsum\\IdeaProjects\\HomeworkToProg\\src\\controlWork\\v15\\file");
        File[] listOfFiles = folder.listFiles();

        File[] filePaths = listOfFiles;

        // Создать пул потоков
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        // Запустить потоки чтения и проверки файлов
        List<Callable<FilePart>> tasks = new ArrayList<>();
        for (File filePath : filePaths) {
            tasks.add(() -> readFile(filePath.toPath(), variantNumber, logWriter));
        }
        List<Future<FilePart>> futures = executorService.invokeAll(tasks);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // Соединить текст из всех частей в один файл
        OutputStreamWriter outputWriter = new OutputStreamWriter(
                new FileOutputStream(String.format(OUTPUT_FILE_TEMPLATE, variantNumber)), StandardCharsets.UTF_8);
        for (Future<FilePart> future : futures) {
            FilePart filePart = future.get();
            outputWriter.write(filePart.getString());
        }
        outputWriter.close();

        // Закрыть лог-файл
        logWriter.close();
    }

    private static BufferedWriter createLogFile(int variantNumber) throws IOException {
        File logFile = new File(String.format(LOG_FILE_TEMPLATE, variantNumber));
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        return new BufferedWriter(new FileWriter(logFile));
    }

    private static FilePart readFile(Path filePath, int variantNumber, BufferedWriter logWriter) throws IOException {
        synchronized (logWriter) {
            try {
                // Прочитать содержимое файла
                FilePart filePart = readFile(filePath);

                // Проверить соответствие количества символов с контрольным числом
                int actualCharCount = filePart.getString().length();
                if (actualCharCount != filePart.getControlNumber()) {
                    logWriter.write(String.format("Ошибка: Количество символов в файле %s не соответствует контрольному числу.", filePath));
                    logWriter.write("\n");
                } else {
                    // Записать в лог-файл
                    logWriter.write(String.format("Прочитали файл %s кол-во байт данных: %d, кол-во считанных символов: %d, контрольное число: %d, номер части: %d.",
                            filePath, filePart.getSize(), actualCharCount, filePart.getControlNumber(), filePart.getNumber()));
                    logWriter.write("\n");
                }
                return filePart;
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private static FilePart readFile(Path filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(filePath);
        int size = bytes[0];
        String string = new String(bytes, 1, size, StandardCharsets.UTF_8);
        int controlNumber = bytes[size + 1];
        int number = bytes[size + 2];
        return new FilePart(size, string, controlNumber, number);
    }
}




