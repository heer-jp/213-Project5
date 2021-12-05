package com.example.rupizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class that handles output for all pizza order(s) for a specific phone number.
 * Allows completion and deletion of pizza order(s).
 * @author Sai Maduri, Heer Patel
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private TextView phoneNumberTxt;
    private ListView ordersLV;
    private TextView subtotalTxt;
    private TextView totalTxt;
    private Button cancelOrderBtn;
    private Button placeOrderBtn;

    /**
     * Open activty_current_order.
     * Allow user to place or cancel order.
     * Display subtotal and total with tax.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curent_order);

        phoneNumberTxt = (TextView) findViewById(R.id.phoneNumberTxt);
        phoneNumberTxt.setText("Phone Number: " + MainActivity.order.getPhoneNumber());
        phoneNumberTxt.setEnabled(false);

        subtotalTxt = (TextView) findViewById(R.id.subtotalTxt2);
        totalTxt = (TextView) findViewById(R.id.totalTxt2);

        ordersLV = (ListView) findViewById(R.id.storeOrdersLV);
        ordersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this);
                alert.setMessage(R.string.remove_pizza_text).setTitle(R.string.remove_pizza_title);
                alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removePizza(position);
                    }
                });
                alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        fillListView();

        cancelOrderBtn = (Button) findViewById(R.id.cancelOrderBtn);
        placeOrderBtn = (Button) findViewById(R.id.placeOrderBtn);

        cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.clearOrder();
                finish();
            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.addToStoreOrders();
                finish();
            }
        });
    }

    /**
     * Remove pizza from list view.
     * @param position of pizza.
     */
    private void removePizza(int position) {
        ArrayList<Pizza> pizzas = MainActivity.order.getOrder();
        MainActivity.order.removePizza(pizzas.get(position));
        if (MainActivity.order.getOrder().size() > 0) {
            fillListView();
        } else {
            MainActivity.clearOrder();
            finish();
        }
    }

    /**
     * Fill list view with current order information.
     */
    public void fillListView() {
        double total = 0;
        ArrayAdapter<Pizza> list = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.order.getOrder());
        ordersLV.setAdapter(list);
        for (Pizza pizza : MainActivity.order.getOrder()) {
            total += pizza.price();
        }
        subtotalTxt.setText(String.format("Subtotal: $%,.2f ", total));
        totalTxt.setText(String.format("Total (with tax): $%,.2f", MainActivity.order.getPrice()));
    }
}