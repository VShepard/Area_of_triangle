package com.company;

import static java.lang.Math.*;
import java.io.*;
import java.util.Scanner;

class Сalculation {
    double areaoftriangle = 0;
    String address[];
    double calcul;
    boolean bool;

    //Получение адреса файлов
    void FileConnection() {
        String input = null;

        do {
            System.out.print("Введите адрес файлов чтения и записи: ");
            bool = true;
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
                input = is.readLine();

            } catch (IOException e) {
                System.out.println(e);
            }

            address = input.split(" ");
            File file1 = new File(address[0]);
            File file2 = new File(address[1]);

            if (!file1.exists()) {
                System.out.println("Введено не верное имя фала для чтения.");
                bool = false;
            }
            if (!file2.exists()) {
                System.out.println("Введено не верное имя фала для записи.");
                bool = false;
            }

        }while(!bool);

    }

    //Чтение из файла
    void FileReading() {
        String line = "";
        int masslines[] = new int[6];

        try {
            File input = new File("C:\\\\Program1\\Otladka\\read.txt");

            Scanner scan = new Scanner(input);

            while(scan.hasNextLine()){
                line = scan.nextLine();
                String lines[] = line.split(" ");

                if(lines.length == 6) {
                    for(int i = 0; i < 6; i++) {
                        masslines[i] = Integer.parseInt(lines[i]);
                    }
                    Tringle(masslines);
                }
            }


        } catch(IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
        }
    }

    //Расчет прощади треугольника
    void Tringle(int coor[]) {
        int coord [] = coor;
        double calcul = 0.5*abs((coord[2] - coord[0])*(coord[5]-coord[1])
                - (coord[4] - coord[0])*(coord[3] - coord[1]));

        if(calcul>areaoftriangle) areaoftriangle = calcul;
    }

    //Запись площади в файл
    void WritingToFile() {
        try (FileWriter writer = new FileWriter(address[1], false)){
            if(areaoftriangle != 0.0) {
                String ch = Double.toString(areaoftriangle);
                System.out.println("Площадь самого большого треугольника - " + areaoftriangle + ".");
                writer.write(ch);
                System.out.println("Значение записанно в файл - " + address[1]);
            }
            else {
                System.out.println("Корретных координат треугольника не найдено.");
                String error = "Корректных координат треугольника не обнаружено.";
                writer.write(error);
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Сalculation run = new Сalculation();
        //Получаем адресс файла
        run.FileConnection();
        //Чтение файла
        run.FileReading();
        //Запись площади в файл
        run.WritingToFile();
    }
}
