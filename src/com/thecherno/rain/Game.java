package com.thecherno.rain;

public class Game {
  public static int width = 300;
// вычисление высоты в зависимости от ширины при соотношении сторон 16:9
  public static int height = width / 16 * 9;
// будет увеличивать масштаб
  public static int scale = 3;
}
