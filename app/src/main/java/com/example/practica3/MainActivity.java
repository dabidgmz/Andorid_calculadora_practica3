package com.example.practica3;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextView = findViewById(R.id.text_view_input);
    }

    public void onClearClick(View view) {
        inputTextView.setText("");
    }

    public void onNumberClick(View view) {
        appendToInput(((Button) view).getText().toString());
    }

    public void onOperatorClick(View view) {
        appendToInput(" " + ((Button) view).getText().toString() + " ");
    }

    public void onEqualClick(View view) {
        try {
            String currentInput = inputTextView.getText().toString();
            double result = evaluateExpression(currentInput);
            inputTextView.setText(String.valueOf(result));
        } catch (Exception e) {
            inputTextView.setText("Error");
        }
    }

    public void onSqrtClick(View view) {
        appendToInput("√");
    }

    private void appendToInput(String text) {
        String currentInput = inputTextView.getText().toString();
        inputTextView.setText(currentInput + text);
    }

    private double evaluateExpression(String expression) {
        try {
            String[] parts = expression.split(" ");

            if (parts.length >= 3) {
                // Evaluar la expresión hasta el segundo operador
                double result = evaluateTwoOperands(parts[0], parts[1], parts[2]);


                for (int i = 3; i < parts.length; i += 2) {
                    result = evaluateTwoOperands(String.valueOf(result), parts[i], parts[i + 1]);
                }

                return result;
            } else {
                throw new IllegalArgumentException("Expresión no válida");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Expresión no válida");
        }
    }

    private double evaluateTwoOperands(String operand1, String operator, String operand2) {
        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("División por cero");
                }
            case "^":
                return Math.pow(num1, num2);
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }
}

