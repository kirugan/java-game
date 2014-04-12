package com.thecherno.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
// что-то для сериализации - в принципе можно было пропустить т.к. типа станд. соглашение в Java
  private static final long serialVersionUID = 1L;
  
  public static int width = 300;
// вычисление высоты в зависимости от ширины при соотношении сторон 16:9
  public static int height = width / 16 * 9;
// будет увеличивать масштаб
  public static int scale = 3;
  
  private Thread thread;
  private JFrame frame;
// для метода run
  private boolean running = false;
  
  public Game(){
    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);
    
    frame = new JFrame();
  }
//  synchronized - для предотвращения memory consistency errors
  public synchronized void start(){
    running = true;
//  запускаем этот класс в новом потоке
    thread = new Thread(this, "Display");
    thread.start();
  }
// чтобы точно удалить все треды | не уверен, что это проблема - чувак упоминал проблему с апплетами
  public synchronized void stop(){
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public void run(){
    while(running){
//    sometimes it called tick
//    update all logic - we will call this thing at fixed rate
      update();
//    we will call it fast as we can
      render();
    }
  }
  
  public void update(){
    
  }
//  делает рендеринг
  public void render(){
//    буффер - временное место для хранения данных | почему не кеш?)
    BufferStrategy bs = getBufferStrategy();
    if(bs == null){
//    количество буфферов - 3, если больше то в принципе никаких улучшений не даст
      createBufferStrategy(3);
      return;
    }
//    getDrawGraphics - дает связь между графическим объектом к-ому можно передать данные
    Graphics g = bs.getDrawGraphics();
//   задает цвет по умолчанию
    g.setColor(Color.BLACK);
//   заполняем окно этим цветом
    g.fillRect(0, 0, getWidth(), getHeight());
//  освобождает ресурсы
    g.dispose();
//  до этого шага данные посчитаны, но еще не размещены
    bs.show();
  }
  
  public static void main(String[] args){
    Game game = new Game();
    game.frame.setResizable(false);
    game.frame.setTitle("Rain");
//  add добавляет компонент в frame - добавляем Game тк он наследуется от Canvas
    game.frame.add(game);
//  установить размер frame`a таким же как и у компонента
    game.frame.pack();
    game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  устанавливаем расположение - отцентрирует наше окно относительно экрана
    game.frame.setLocationRelativeTo(null);
    game.frame.setVisible(true);
    
    game.start();
  }
}
