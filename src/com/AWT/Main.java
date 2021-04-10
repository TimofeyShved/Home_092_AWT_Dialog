package com.AWT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    // создаем в классе пееменные
    private Frame mainFrame; // фрайм и 2 метки (лэйблы) и панель
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public Main() { // ----------------------------------------- конструктор для нашего класса
        prepareGUI(); // и вызываемый метод prepareGUI
    }

    public static void main(String[] args) {
        Main myMainClass = new Main(); // создание наешого основного класса
        myMainClass.showDialogDemo(); // и вызываемый метод showScrollbarDemo
    }

    // ----------------------------------------------------- описывает основное визуальное отображение
    private void prepareGUI() {
        mainFrame = new Frame("Java AWT Dialog");  // инициализация фрэйма
        mainFrame.setSize(400, 400); // размеры
        mainFrame.setLayout(new GridLayout(3, 1));// расположение объектов на форме

        mainFrame.addWindowListener(new WindowAdapter() {// обработка событий
            public void windowClosing(WindowEvent windowEvent) { // принажатии на кнопку "Закрыть"
                System.exit(0);
            }
        });

        // метки
        headerLabel = new Label(); // инициализация
        headerLabel.setAlignment(Label.CENTER);// в центре
        statusLabel = new Label();// инициализация
        statusLabel.setAlignment(Label.CENTER);// в центре
        statusLabel.setSize(350, 100);// размеры

        controlPanel = new Panel();// новая панель
        controlPanel.setLayout(new FlowLayout()); // расположение объектов на форме (лайаут)

        // добавление объектов на форму
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true); // видимость формы true
    }

    //----------------------------------------------------- Что будет в showDialogDemo
    private void showDialogDemo(){
        headerLabel.setText("Контрол в действии: Dialog");
        Button showAboutDialogButton = new Button("открыть диалоговое окно");
        showAboutDialogButton.addActionListener(new ActionListener() {// дейтвие при активации
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog aboutDialog = new AboutDialog(mainFrame); // новый класс, предаёт настройки основного
                aboutDialog.setVisible(true); //отобразить
            }
        });

        // добавление объектов на форму
        controlPanel.add(showAboutDialogButton);
        mainFrame.setVisible(true); // видимость формы true
    }

    // ------------------------  новый класс AboutDialog
    class AboutDialog extends Dialog { // наследует класс диалог, поэтому имеет отдельное окно
        public AboutDialog(Frame parent){
            super(parent, true); // родительскую форму - блокируем
            setBackground(Color.gray); // заливка
            setLayout(new BorderLayout()); // расположение объектов
            Panel panel = new Panel(); // создание панели
            panel.add(new Button("Закрыть")); // добавление кнопки на панель
            add("South", panel); //добавление объектов на форму
            setSize(200,200); // размеры

            addWindowListener(new WindowAdapter() { // действие
                public void windowClosing(WindowEvent windowEvent){
                    dispose(); // закрыть окно
                }
            });
        }

        public boolean action(Event evt, Object arg){
            if(arg.equals("Close")){ // если имя объекта - Close
                dispose(); // закрыть окно
                return true;
            }
            return false;
        }

        public void paint(Graphics g){ // нарисовать
            g.setColor(Color.white); // цвет текста
            g.drawString("Наш текст для нового окна", 25,70 );
            g.drawString("Версия 1.0", 60, 90);
        }
    }
}