package com.example.rupizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static EditText phoneNumberTxt;
    private ImageView orderDeluxeBtn;
    private ImageView orderHawaiianBtn;
    private ImageView orderPepperoniBtn;
    private ImageView viewCartBtn;
    private ImageView viewStoreOrdersBtn;
    static Order order;
    static StoreOrders storeOrders = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberTxt = (EditText) findViewById(R.id.editTextPhone);
        orderDeluxeBtn = (ImageView) findViewById(R.id.deluxePizza);
        orderHawaiianBtn = (ImageView) findViewById(R.id.hawaiinPizza);
        orderPepperoniBtn = (ImageView) findViewById(R.id.pepperoniPizza);
        viewCartBtn = (ImageView) findViewById(R.id.currentOrder);
        viewStoreOrdersBtn = (ImageView) findViewById(R.id.storeOrders);

        orderDeluxeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomizationActivityDeluxe();
            }
        });

        orderHawaiianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomizationActivityHawaiian();
            }
        });

        orderPepperoniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomizationActivityPepperoni();
            }
        });

        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrentOrderActivity();
            }
        });

        viewStoreOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoreOrdersActivity();
            }
        });
    }

    public void openCustomizationActivityDeluxe() {
        if (validateOrder()) {
            if (order == null) {
                order = new Order(phoneNumberTxt.getText().toString());
                phoneNumberTxt.setEnabled(false);
            }
            Intent intent = new Intent(MainActivity.this, CustomizationActivity.class);
            intent.putExtra("USER_DATA", new String[]{phoneNumberTxt.getText().toString(), "DELUXE", "SMALL", "CHICKEN", "SAUSAGE", "PEPPERS", "ONIONS",
                    "OLIVES"});
            startActivity(intent);
        }
    }

    public void openCustomizationActivityHawaiian() {
        if (validateOrder()) {
            if (order == null) {
                order = new Order(phoneNumberTxt.getText().toString());
                phoneNumberTxt.setEnabled(false);
            }
            Intent intent = new Intent(MainActivity.this, CustomizationActivity.class);
            intent.putExtra("USER_DATA", new String[]{phoneNumberTxt.getText().toString(), "HAWAIIAN", "SMALL", "CHICKEN", "PINEAPPLE"});
            startActivity(intent);
        }
    }

    public void openCustomizationActivityPepperoni() {
        if (validateOrder()) {
            if (order == null) {
                order = new Order(phoneNumberTxt.getText().toString());
                phoneNumberTxt.setEnabled(false);
            }
            Intent intent = new Intent(MainActivity.this, CustomizationActivity.class);
            intent.putExtra("USER_DATA", new String[]{phoneNumberTxt.getText().toString(), "PEPPERONI", "SMALL", "PEPPERONI"});
            startActivity(intent);
        }
    }

    public void openCurrentOrderActivity() {
        if (order != null) {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        } else {
            displayToast("Add a pizza to start your order.");
        }
    }

    public void openStoreOrdersActivity() {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }

    public static void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

    private boolean validateOrder() {
        String phoneNumber = phoneNumberTxt.getText().toString().trim();
        if (phoneNumber.length() == 0) {
            displayToast("Please enter a phone number.");
            return false;
        } else if (phoneNumber.matches("[0-9]+")) {
            if (phoneNumber.length() != 10) {
                displayToast("Phone numbers must be 10 digits.");
                return false;
            } else {
//                for (Order order : storeOrders.getOrders()) {
//                    if (order.getPhoneNumber().equals(phoneNumber)) {
//                        displayToast("An order has already been placed with that phone number.");
//                        return false;
//                    }
//                }
            }
        } else {
            displayToast("Please use only digits for your phone number.");
            return false;
        }
        return true;
    }

    public static void addToStoreOrders() {
        storeOrders.addOrder(order);
        clearOrder();
    }

    public static void clearOrder() {
        phoneNumberTxt.setText("");
        order = null;
        phoneNumberTxt.setEnabled(true);
    }

    private void displayToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}