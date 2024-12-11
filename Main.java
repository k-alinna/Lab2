import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        out.println("Введите два числа n и m:");

        int n = in.nextInt(); // считываем размеры массива
        int m = in.nextInt();
        in.nextLine(); // очистка буфера ввода после считывания чисел

        out.println("Введите n*m элементов массива,каждый с новой строки:");
        String[][] a = new String[n][m]; // создаем двумерный массив строк размером N на M


        // считываем элементы массива
        for (int i = 0; i < n; i++) {        // цикл по строкам массива
            for (int j = 0; j < m; j++) {   // цикл по столбцам массива
                a[i][j] = in.nextLine(); // сохраняем в соответствующую ячейку массива
            }
        }

        // сортировка столбцов по суммарной длине строк и количеству слов
        for (int column = 0; column < m; column++) {    // цикл по каждому столбцу
            for (int nextcolumn = column + 1; nextcolumn < m; nextcolumn++) {  // цикл для сравнения текущего столбца с последующими
                int sumrow = 0, sumnextrow = 0; // переменные для хранения сумм длины строк в текущем и следующем столбце
                int countword = 0, countnextword = 0; // переменные для хранения количества слов в текущем и следующем столбцах

                for (int row = 0; row < n; row++) {  // цикл по строкам для подсчета длины и количества слов
                    sumrow += a[row][column].length(); // суммируем длину строк в текущем столбце
                    sumnextrow += a[row][nextcolumn].length(); // суммируем длину строк в следующем столбце
                    countword += countWords(a[row][column]); // считаем количество слов в текущем столбце
                    countnextword += countWords(a[row][nextcolumn]);  // считаем количество слов в следующем столбце
                }

                if ((sumrow < sumnextrow) || ((sumrow == sumnextrow) && (countword < countnextword))) {  // проверяем, нужно ли менять местами столбцы
                    // если да, меняем местами столбцы
                    for (int row = 0; row < n; row++) {   // цикл по строкам для обмена значениями между столбцами
                        String temp = a[row][column]; // временная переменная для хранения значения текущего столбца
                        a[row][column] = a[row][nextcolumn]; // переносим значение следующего столбца в текущий
                        a[row][nextcolumn] = temp; // переносим временное значение в следующий столбец
                    }
                }
            }
        }
        //подсчёт слов с заглавной буквы
        int count = 0; // переменная для хранения общего количества слов с заглавной буквы
        String capitalwords = ""; // строка для хранения найденных слов с заглавной буквы
        for (int i = 0; i < n; i++) { // цикл по строкам массива
            for (int j = 0; j < m; j++) { // цикл по столбцам массива
                String [] words = a[i][j].split("\s+"); // разделяем строку на слова по пробелам
                for (String word : words) {  // цикл по каждому слову в строке (в данном случае в строковом массиве)
                    if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')) { // проверяем, начинается ли оно с заглавной буквы
                        count++; // увеличиваем общий счетчик слов с заглавной буквы
                        capitalwords += word + " "; // добавляем слово к строке capitalWords
                    }
                }
            }
        }

        out.println("Количество слов с заглавной буквы: " + count); // выводим общее количество найденных слов
        out.print("Слова с заглавной буквы: " + capitalwords); // выводим найденные слова

        out.println();

        out.println("Массив с развернутыми элементами:");
        // вывод элементов массива с разворотом строк и пометкой палиндромов
        for (int i = 0; i < n; i++) { // цикл по строкам массива
            for (int j = 0; j < m; j++) { // цикл по столбцам массива
                String reversed = reverseString(a[i][j]); // переворачиваем строку с помощью вспомогательного метода reverseString
                if (isPalindrome(a[i][j])) { // проверяем, является ли строка палиндромом
                    reversed += "*";  // если да, добавляем '*' к перевернутой строке
                }
                out.print(reversed + " "); // выводим перевернутую строку на экран
            }
            out.println(); // переходим на новую строку после вывода всех столбцов текущей строки массива
        }

        out.println("Массив c элементами без повторов с замененными пробелами на '-':");
        // замена пробелов на '-', удаление повторяющихся символов и вывод
        for (int i = 0; i < n; i++) { // цикл по строкам массива
            for (int j = 0; j < m; j++) { // цикл по столбцам массива
                String modified = a[i][j].replace(" ", "-"); // заменяем все пробелы на '-'
                modified = removeDuplicates(modified); // удаляем повторяющиеся символы с помощью вспомогательного метода removeDuplicates
                out.print(modified + " "); // выводим измененную строку на экран
            }
            out.println(); // переходим на новую строку после вывода всех столбцов текущей строки массива
        }
    }

    public static int countWords(String str) { // вспомогательный метод для подсчета количества слов в строке
        if (str.isEmpty())
            return 0;  // если строка пустая, возвращаем 0
        String[] words = str.trim().split("\s+"); // убираем лишние пробелы в начале и конце и разделяем строку на слова по пробелам
        return words.length; // возвращаем количество найденных слов
    }

    public static String reverseString(String str) { // вспомогательный метод для переворота строки
        char [] charArray = str.toCharArray(); // преобразуем строку в массив символов
        String reversed = "";  // создаем пустую строку для хранения перевернутой строки
        for (int i = charArray.length - 1; i >= 0; i--) { // проходимся по массиву символов в обратном порядке
            reversed += charArray[i]; // добавляем каждый символ к перевернутой строке
        }
        return reversed; // возвращаем перевернутую строку
    }


    public static boolean isPalindrome(String str) { // вспомогательный метод для проверки, является ли строка палиндромом
        String row = str.replaceAll("\s+", "").toLowerCase(); // убираем пробелы и приводим к нижнему регистру для сравнения
        return row.equals(reverseString(row)); // сравниваем очищенную строку с ее перевернутой версией
    }

    public static String removeDuplicates(String str) { // вспомогательный метод для удаления повторяющихся символов из строки
        String result = "";  // создаем пустую строку для хранения результата
        for (int i = 0; i < str.length(); i++) { // проходимся по каждому символу строки
            if ((result.indexOf(str.charAt(i)) == -1) || (str.charAt(i) == '-')) {// проверяем наличие символа в результате и не равен ли он "-"
                result += str.charAt(i); // если нет, добавляем его к результату
            }
        }
        return result; // возвращаем результат без дубликатов
    }
}