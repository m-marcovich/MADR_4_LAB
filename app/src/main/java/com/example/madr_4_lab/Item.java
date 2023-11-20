package com.example.madr_4_lab;

import java.io.Serializable;

public class Item implements Serializable {
    private String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
