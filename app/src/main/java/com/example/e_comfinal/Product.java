package com.example.e_comfinal;

public class Product{
  
  private String name;
  private int price;
  private int stock;
  private String imageUrl;
  
  public Product(String name, int price, int stock, String imageUrl){
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public String getName(){
    return this.name;
  }
  
  public void setPrice(int price){
    this.price = price;
  }
    
  public int getPrice(){
    return this.price;
  }
    
  public void setStock(int price){
    this.stock = stock;
  }
  
  public int getStock(int stock){
    return this.stock;
  }
      
  public void setImageUrl(String imageUrl){
      this.imageUrl = imageUrl;
  }
    
  public String getImageUrl(){
    return this.imageUrl;
  }
  
}