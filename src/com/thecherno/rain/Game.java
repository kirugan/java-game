package com.thecherno.rain;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements Runnable{
  public static int width = 300;
// вычисление высоты в зависимости от ширины при соотношении сторон 16:9
  public static int height = width / 16 * 9;
// будет увеличивать масштаб
  public static int scale = 3;
  
  private Thread thread;
  
//  synchronized - для предотвращения memory consistency errors
  public synchronized void start(){
//  запускаем этот класс в новом потоке
    thread = new Thread(this, "Display");
    thread.start();
  }
// чтобы точно удалить все треды | не уверен, что это проблема - чувак упоминал проблему с апплетами
  public synchronized void stop(){
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
