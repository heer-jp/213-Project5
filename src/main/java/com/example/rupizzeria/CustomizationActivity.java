package com.example.rupizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomizationActivity extends AppCompatActivity {

    private ImageView pizzaImage;
    private Spinner sizeSpinner;
    private TextView subtotalTxt;
    private Button placeOrderBtn;
    private CheckBox baconCB;
    private CheckBox onionsCB;
    private CheckBox chickenCB;
    private CheckBox pepperoniCB;
    private CheckBox extracheeseCB;
    private CheckBox peppersCB;
    private CheckBox jalapenosCB;
    private CheckBox pineappleCB;
    private CheckBox mushroomsCB;
    private CheckBox sausageCB;
    private CheckBox olivesCB;

    private String[] userData;
    private String pizzaType;
    private String size;

    private int toppingsCount = 0;
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Pizza tmpPizza;

    private double SMALL_SCALE = .6;
    private double MEDIUM_SCALE = .8;
    private double LARGE_SCALE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        pizzaImage = (ImageView) findViewById(R.id.pizzaImage);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpn);
        subtotalTxt = (TextView) findViewById(R.id.subtotalTxt);
        placeOrderBtn = (Button) findViewById(R.id.addPizzaBtn);
        baconCB = (CheckBox) findViewById(R.id.baconCB);
        onionsCB = (CheckBox) findViewById(R.id.onionsCB);
        chickenCB = (CheckBox) findViewById(R.id.chickenCB);
        pepperoniCB = (CheckBox) findViewById(R.id.pepperoniCB);
        extracheeseCB = (CheckBox) findViewById(R.id.extracheeseCB);
        peppersCB = (CheckBox) findViewById(R.id.peppersCB);
        jalapenosCB = (CheckBox) findViewById(R.id.jalapenosCB);
        pineappleCB = (CheckBox) findViewById(R.id.pineappleCB);
        mushroomsCB = (CheckBox) findViewById(R.id.mushroomsCB);
        sausageCB = (CheckBox) findViewById(R.id.sausageCB);
        olivesCB = (CheckBox) findViewById(R.id.olivesCB);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder();
            }
        });

        baconCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        onionsCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        chickenCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        pepperoniCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        extracheeseCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        peppersCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        jalapenosCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        pineappleCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        mushroomsCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        sausageCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        olivesCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckBoxClicked(v);
            }
        });

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                displaySubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                displaySubtotal();
            }
        });

        Intent intent = getIntent();
        userData = intent.getStringArrayExtra("USER_DATA");
        pizzaType = userData[1];
        size = userData[2];
        switch (pizzaType) {
            case "DELUXE": pizzaImage.setImageResource(R.drawable.deluxe); System.out.println("set deluxe image"); break;
            case "HAWAIIAN": pizzaImage.setImageResource(R.drawable.hawaiian); break;
            case "PEPPERONI": pizzaImage.setImageResource(R.drawable.pepperonipizza); break;
        }
        setDefaultToppings();
        displaySubtotal();
    }

    public void addToOrder() {
        try {
            MainActivity.addPizzaToOrder(tmpPizza);
        } catch (Exception e) {
            //put error toast here
        }
        finish();
    }

    public void onCheckBoxClicked(View view) {
        CheckBox cb = (CheckBox) view;
        boolean checked = cb.isChecked();
        Topping topping = getTopping(cb.getText().toString().toUpperCase());
        if (!checked) {
            if (toppingsCount > 0) {
                toppingsCount--;
                toppings.remove(topping);
            }
        } else {
            if (toppingsCount >= Pizza.MAX_TOPPINGS_COUNT) {
                cb.setChecked(false);
                displayToast("Can not add more than 7 toppings.");
            } else {
                toppings.add(topping);
                toppingsCount++;
            }
        }
        displaySubtotal();
    }


    public void setDefaultToppings() {
        if (pizzaType.equals(Pizza.DELUXE)) {
            chickenCB.setChecked(true);
            sausageCB.setChecked(true);
            peppersCB.setChecked(true);
            onionsCB.setChecked(true);
            olivesCB.setChecked(true);
            loadToppings();
            toppingsCount = Pizza.DELUXE_TOPPINGS_COUNT;
        } else if (pizzaType.equals(Pizza.HAWAIIAN)) {
            chickenCB.setChecked(true);
            pineappleCB.setChecked(true);
            loadToppings();
            toppingsCount = Pizza.HAWAIIAN_TOPPINGS_COUNT;
        } else {
            pepperoniCB.setChecked(true);
            loadToppings();
            toppingsCount = Pizza.PEPPERONI_TOPPINGS_COUNT;
        }
    }

    public void loadToppings() {
        int loadToppingsStartIndex = 3;
        for (int i = loadToppingsStartIndex; i < userData.length; i++)
            toppings.add(getTopping(userData[i].toUpperCase()));
    }

    public static Topping getTopping(String topping) {
        return Topping.valueOf(topping.toUpperCase());
    }

    public static Size getSize(String size) {
        return Size.valueOf(size.toUpperCase());
    }

    public void displaySubtotal() {
        tmpPizza = PizzaMaker.createPizza(pizzaType, getSize(sizeSpinner.getSelectedItem().toString()), toppings);
        String price = String.format("%,.2f", tmpPizza.price());
        subtotalTxt.setText("Subtotal: $" + price);
    }

    private void displayToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}