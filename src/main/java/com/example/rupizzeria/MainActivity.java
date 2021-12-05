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

/**
 * Class that handles input, output, and button events that open corresponding activities.
 * @author Sai Maduri, Heer Patel
 */
public class MainActivity extends AppCompatActivity {

    private static EditText phoneNumberTxt;
    private ImageView orderDeluxeBtn;
    private ImageView orderHawaiianBtn;
    private ImageView orderPepperoniBtn;
    private ImageView viewCartBtn;
    private ImageView viewStoreOrdersBtn;
    static Order order;
    static StoreOrders storeOrders = new StoreOrders();

    /**
     * Open activty_main upon clicking app icon.
     * Calls corresponding method for each button click in main menu.
     * @param savedInstanceState
     */
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

    /**
     * Sends user information to CustomizationActivty class to open
     * activity_customization with deluxe settings.
     */
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

    /**
     * Sends user information to CustomizationActivty class to open
     * activity_customization with hawaiin settings.
     */
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

    /**
     * Sends user information to CustomizationActivty class to open
     * activity_customization with pepperoni settings.
     */
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

    /**
     * Send user information to CurrentOrderActivity class to open
     * activity_current_order.
     */
    public void openCurrentOrderActivity() {
        if (order != null) {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        } else {
            displayToast("Add a pizza to start your order.");
        }
    }

    /**
     * Send user information to StoreOrdersActivity class to open
     * activity_store_orders.
     */
    public void openStoreOrdersActivity() {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * Add pizza to pizza order.
     * @param pizza details chosen by user
     */
    public static void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

    /**
     * Validate user phone number.
     * @return false if invalid, true if valid.
     */
    private boolean validateOrder() {
        String phoneNumber = phoneNumberTxt.getText().toString().trim();
        if (phoneNumber.length() == 0) {
            displayToast("Please enter a phone number.");
            return false;
        } else if (phoneNumber.matches("[0-9]+")) {
            if (phoneNumber.length() != 10) {
                displayToast("Phone numbers must be 10 digits.");
                return false;
            }
        } else {
            displayToast("Please use only digits for your phone number.");
            return false;
        }
        return true;
    }

    /**
     * Add pizza order to store order.
     */
    public static void addToStoreOrders() {
        storeOrders.addOrder(order);
        clearOrder();
    }

    /**
     * Clear order list.
     */
    public static void clearOrder() {
        phoneNumberTxt.setText("");
        order = null;
        phoneNumberTxt.setEnabled(true);
    }

    /**
     * Display warning messages.
     * @param s warning messages.
     */
    private void displayToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}