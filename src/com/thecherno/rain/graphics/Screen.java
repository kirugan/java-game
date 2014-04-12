package com.thecherno.rain.graphics;
// иногда называют Render
public class Screen {
  
  private int width, height;
  public int[] pixels;
  
  public Screen(int width, int height){
    this.width = width;
    this.height = height;
    
    pixels = new int[width * height];
  }
}
