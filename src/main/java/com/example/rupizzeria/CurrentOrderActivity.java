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

public class CurrentOrderActivity extends AppCompatActivity {

    private TextView phoneNumberTxt;
    private ListView ordersLV;
    private TextView subtotalTxt;
    private TextView totalTxt;
    private Button cancelOrderBtn;
    private Button placeOrderBtn;

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
                alert.setMessage("Remove pizza").setTitle("Remove pizza");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removePizza(position);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        displayToast(getString(R.string.itemno));
                        System.out.println("did not remove pizza");
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
//        ArrayAdapter<Pizza> list = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.order.getOrder());
//        ordersLV.setAdapter(list);
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