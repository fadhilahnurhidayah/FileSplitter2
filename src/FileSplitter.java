import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan path file .txt: ");
        String filePath = scanner.nextLine();

        System.out.print("Masukkan ukuran potongan (jumlah karakter): ");
        int chunkSize = scanner.nextInt();

        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            Queue<String> chunks = new LinkedList<>();
            String text = content.toString();
            int length = text.length();
            for (int i = 0; i < length; i += chunkSize) {
                int end = Math.min(length, i + chunkSize);
                chunks.add(text.substring(i, end));
            }

            int partNumber = 1;
            while (!chunks.isEmpty()) {
                String chunk = chunks.poll();
                try (FileWriter writer = new FileWriter("part_" + partNumber + ".txt")) {
                    writer.write(chunk);
                }
                System.out.println("Menyimpan potongan " + partNumber + ": " + chunk);
                partNumber++;
            }

            System.out.println("File telah dipotong dan disimpan.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
