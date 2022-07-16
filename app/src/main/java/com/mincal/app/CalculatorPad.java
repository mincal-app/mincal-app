package com.mincal.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.w3c.dom.Text;

public class CalculatorPad extends Fragment {

  // Calculator Global Variables

  private static String calculatorCount = "0";
  private static int calculatorDotCount = 0;
  private static long resultCount = 0;
  private static ArrayList<Double> calculatorStack = new ArrayList<>();
  private static ArrayList<String> symbolStack = new ArrayList<>();

  // Views

  // Number Pad

  TextView one;
  TextView two;
  TextView three;
  TextView four;
  TextView five;
  TextView six;
  TextView seven;
  TextView eight;
  TextView nine;
  TextView zero;
  TextView power;

  // Rounded Buttons

  TextView parentheses;
  TextView divide;
  TextView multiply;
  TextView subtract;
  TextView add;
  TextView dot;
  TextView euler;
  TextView root;

  // Special Buttons

  ImageView back;
  TextView clean;
  TextView powertools;
  TextView result;

  // Calculator Screen

  TextView screenResult;
  TextView screenResultFirstStack;
  TextView screenResultSecondStack;

  // TinyDB

  TinyDB tinydb;
  String userColor;

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;

  public CalculatorPad() {
    // Required empty public constructor
  }

  public static CalculatorPad newInstance(String param1, String param2) {
    CalculatorPad fragment = new CalculatorPad();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public void onViewCreated(@NonNull View view,
                            @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // Initialize Calculator Stack

    calculatorStack.add(Double.parseDouble(calculatorCount));
    symbolStack.add("+");

    // Views

    one = getView().findViewById(R.id.pad_numbers_one);
    two = getView().findViewById(R.id.pad_numbers_two);
    three = getView().findViewById(R.id.pad_numbers_three);
    four = getView().findViewById(R.id.pad_numbers_four);
    five = getView().findViewById(R.id.pad_numbers_five);
    six = getView().findViewById(R.id.pad_numbers_six);
    seven = getView().findViewById(R.id.pad_numbers_seven);
    eight = getView().findViewById(R.id.pad_numbers_eight);
    nine = getView().findViewById(R.id.pad_numbers_nine);
    zero = getView().findViewById(R.id.pad_numbers_zero);
    power = getView().findViewById(R.id.pad_numbers_power);

    // Rounded Buttons

    parentheses = getView().findViewById(R.id.pad_top_parentheses);
    divide = getView().findViewById(R.id.pad_top_divide);
    multiply = getView().findViewById(R.id.pad_top_multiply);
    subtract = getView().findViewById(R.id.pad_right_minus);
    add = getView().findViewById(R.id.pad_right_plus);
    dot = getView().findViewById(R.id.pad_bottom_dot);
    euler = getView().findViewById(R.id.pad_bottom_euler);
    root = getView().findViewById(R.id.pad_bottom_root);

    // Special Buttons

    back = getView().findViewById(R.id.pad_numbers_back);
    clean = getView().findViewById(R.id.pad_top_clean);
    powertools = getView().findViewById(R.id.pad_right_more_tools);
    result = getView().findViewById(R.id.pad_right_equal);

    // Elements as Array

    TextView[] numberPad = {one,   two,   three, four, five, six,
                            seven, eight, nine,  zero, power};
    TextView[] roundedButtonsPad = {
        parentheses, divide, multiply, subtract, add, dot, euler, root, result};

    // Calculator Screen

    screenResult = (TextView)getActivity().findViewById(R.id.screen_result);
    screenResultFirstStack =
        (TextView)getActivity().findViewById(R.id.screen_result_first_stack);
    screenResultSecondStack =
        (TextView)getActivity().findViewById(R.id.screen_result_second_stack);

    // Instance of SharedPreferences to store the user's name.

    tinydb = new TinyDB(getContext());
    userColor = tinydb.getString("user_color");

    // Modify gradient color based on user's selected color.

    for (int i = 0; i < numberPad.length; i++) {
      if ("orange".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.orange));
      } else if ("red".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.red));
      } else if ("blue".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.blue));
      } else if ("purple".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.purple));
      } else if ("green".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.green));
      } else if ("yellow".equals(userColor)) {
        numberPad[i].setTextColor(getResources().getColor(R.color.yellow));
      } else {
        numberPad[i].setTextColor(getResources().getColor(R.color.black));
      }
    }

    for (int i = 0; i < roundedButtonsPad.length; i++) {
      if ("orange".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_orange);
      } else if ("red".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_red);
      } else if ("blue".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_blue);
      } else if ("purple".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_purple);
      } else if ("green".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_green);
      } else if ("yellow".equals(userColor)) {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_yellow);
      } else {
        roundedButtonsPad[i].setBackgroundResource(
            R.drawable.calculator_pad_rounded_purple);
      }
    }

    // Change color for special buttons

    if ("orange".equals(userColor)) {
      clean.setBackgroundResource(
          R.drawable.calculator_pad_rounded_right_orange);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_orange);
      back.setImageResource(R.drawable.ic_back_orange);
    } else if ("red".equals(userColor)) {
      clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_red);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_red);
      back.setImageResource(R.drawable.ic_back_red);
    } else if ("blue".equals(userColor)) {
      clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_blue);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_blue);
      back.setImageResource(R.drawable.ic_back_blue);
    } else if ("purple".equals(userColor)) {
      clean.setBackgroundResource(
          R.drawable.calculator_pad_rounded_right_purple);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_purple);
      back.setImageResource(R.drawable.ic_back_purple);
    } else if ("green".equals(userColor)) {
      clean.setBackgroundResource(
          R.drawable.calculator_pad_rounded_right_green);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_green);
      back.setImageResource(R.drawable.ic_back_green);
    } else if ("yellow".equals(userColor)) {
      clean.setBackgroundResource(
          R.drawable.calculator_pad_rounded_right_yellow);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_yellow);
      back.setImageResource(R.drawable.ic_back_yellow);
    } else {
      clean.setBackgroundResource(
          R.drawable.calculator_pad_rounded_right_purple);
      powertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_purple);
      back.setImageResource(R.drawable.ic_back_purple);
    }

    // Functional Elements

    // Update screen numbers.

    screenResult.setText(calculatorCount);

    one.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("1");
        updateScreen();
      }
    });

    two.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("2");
        updateScreen();
      }
    });

    three.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("3");
        updateScreen();
      }
    });

    four.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("4");
        updateScreen();
      }
    });

    five.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("5");
        updateScreen();
      }
    });

    six.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("6");
        updateScreen();
      }
    });

    seven.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("7");
        updateScreen();
      }
    });

    eight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("8");
        updateScreen();
      }
    });

    nine.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("9");
        updateScreen();
      }
    });

    zero.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        updateCount("0");
        updateScreen();
      }
    });

    dot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (!calculatorCount.contains(".")) {
          calculatorDotCount++;
          calculatorCount += ".";
          screenResult.setText(calculatorCount);
        }
      }
    });

    // Back button

    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculatorCount =
            calculatorCount.substring(0, calculatorCount.length() - 1);
        screenResult.setText(addCommas(Double.parseDouble(calculatorCount)));

        // If calculator is empty after deleting, update to zero.

        if (calculatorCount.isEmpty()) {
          calculatorCount = "0";
          screenResult.setText(addCommas(Double.parseDouble(calculatorCount)));
        }
      }
    });

    // Symbol usage.

    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ("0".equals(calculatorCount)) {
          symbolStack.set(symbolStack.size() - 1, "+");
          updateStack(screenResultFirstStack, screenResultSecondStack);
        } else {
          calculatorStack.add(Double.parseDouble(calculatorCount));
          symbolStack.add("+");
          updateStack(screenResultFirstStack, screenResultSecondStack);

          // Add symbol to calculator screen.

          calculatorCount = "0";
        }

        updateScreen();

        Log.d("Testing Result Plus", "Current Count: " + calculatorCount);
        Log.d("Testing Result Plus", "Current Stack: " + calculatorStack);
        Log.d("Testing Result Plus", "Current Symbol Stack: " + symbolStack);
      }
    });

    subtract.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ("0".equals(calculatorCount)) {
          symbolStack.set(symbolStack.size() - 1, "-");
          updateStack(screenResultFirstStack, screenResultSecondStack);
        } else {
          calculatorStack.add(Double.parseDouble(calculatorCount));
          symbolStack.add("-");
          updateStack(screenResultFirstStack, screenResultSecondStack);

          // Add symbol to calculator screen.

          calculatorCount = "0";
        }

        updateScreen();

        Log.d("Testing Result Minus", "Current Count: " + calculatorCount);
        Log.d("Testing Result Minus", "Current Stack: " + calculatorStack);
        Log.d("Testing Result Minus", "Current Symbol Stack: " + symbolStack);
      }
    });

    multiply.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ("0".equals(calculatorCount)) {
          symbolStack.set(symbolStack.size() - 1, "x");
          updateStack(screenResultFirstStack, screenResultSecondStack);
        } else {
          calculatorStack.add(Double.parseDouble(calculatorCount));
          symbolStack.add("x");
          updateStack(screenResultFirstStack, screenResultSecondStack);

          // Add symbol to calculator screen.

          calculatorCount = "0";
        }

        updateScreen();

        Log.d("Testing Result Times", "Current Count: " + calculatorCount);
        Log.d("Testing Result Times", "Current Stack: " + calculatorStack);
        Log.d("Testing Result Times", "Current Symbol Stack: " + symbolStack);
      }
    });

    divide.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ("0".equals(calculatorCount)) {
          symbolStack.set(symbolStack.size() - 1, "÷");
          updateStack(screenResultFirstStack, screenResultSecondStack);
        } else {
          calculatorStack.add(Double.parseDouble(calculatorCount));
          symbolStack.add("÷");
          updateStack(screenResultFirstStack, screenResultSecondStack);

          // Add symbol to calculator screen.

          calculatorCount = "0";
        }

        updateScreen();
      }
    });

    // Core buttons

    clean.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculatorCount = "0";
        calculatorStack.clear();
        symbolStack.clear();
        screenResult.setText("0");
        updateStack(screenResultFirstStack, screenResultSecondStack);

        // Initialize Calculator Stack

        calculatorStack.add(Double.parseDouble(calculatorCount));
        symbolStack.add("+");
      }
    });

    // Equals/Results Button

    result.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        // Add current value

        calculatorStack.add(Double.parseDouble(calculatorCount));
        // symbolStack.add(Double.parseDouble(calculatorCount) >= 0 ? "+" :
        // "-");

        // If stack.size() is odd, add 0.

        if (calculatorStack.size() % 2 != 0) {
          calculatorStack.add(0.0);
          symbolStack.add(Double.parseDouble(calculatorCount) >= 0 ? "+" : "-");
        }

        // Process Stacks

        Log.d("Testing Result Result", "Current Count: " + calculatorCount);
        Log.d("Testing Result Result", "Current Stack: " + calculatorStack);
        Log.d("Testing Result Result", "Current Symbol Stack: " + symbolStack);

        screenResult.setText(addCommas(processResult() * 1.0));
        updateStack(screenResultFirstStack, screenResultSecondStack);
      }
    });

    // PowerTools Menu

    powertools.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentManager fragmentManager =
            getActivity().getSupportFragmentManager();
        FragmentTransaction powerMenuTransaction =
            fragmentManager.beginTransaction();
        FragmentTransaction blurTransaction =
            fragmentManager.beginTransaction();
        powerMenuTransaction.setCustomAnimations(R.anim.swipe_in_left,
                                                 R.anim.swipe_out_left);
        blurTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        PowertoolsMenu powertoolsMenu = new PowertoolsMenu();
        BlurFragment blurFragment = new BlurFragment();
        blurTransaction
            .add(R.id.calculator_pad_container, blurFragment,
                 "blur_powertools_menu")
            .commit();
        powerMenuTransaction
            .add(R.id.calculator_pad_container, powertoolsMenu,
                 "powertools_menu")
            .commit();
      }
    });
  }

  // Update Count based on number.

  public void updateScreen() {
    if ("0".equals(calculatorCount)) {
      screenResult.setText(symbolStack.get(symbolStack.size() - 1) + " " +
                           addCommas(Double.parseDouble(calculatorCount)));
    } else {
      screenResult.setText(symbolStack.get(symbolStack.size() - 1) + " " +
                           addCommas(Double.parseDouble(calculatorCount)));
    }
  }

  public void updateCount(String number) {
    if ("0".equals(calculatorCount) && "0".equals(number)) {
      calculatorCount += ".0";
    } else if ("0".equals(calculatorCount)) {
      calculatorCount = number;
    } else {
      calculatorCount += number;
    }
  }

  public void updateStack(TextView firstStack, TextView secondStack) {

    // Set symbols.

    String firstStackSymbol = "";
    String secondStackSymbol = "";

    if (calculatorStack.size() >= 3) {
      firstStackSymbol = symbolStack.get(symbolStack.size() - 2);
      secondStackSymbol = symbolStack.get(symbolStack.size() - 3);
      firstStack.setText(
          String.valueOf(firstStackSymbol + " " +
                         calculatorStack.get(calculatorStack.size() - 1)));
      secondStack.setText(
          String.valueOf(secondStackSymbol + " " +
                         calculatorStack.get(calculatorStack.size() - 2)));
    } else if (calculatorStack.size() >= 2) {
      firstStackSymbol = symbolStack.get(symbolStack.size() - 2);
      firstStack.setText(
          String.valueOf(firstStackSymbol + " " +
                         calculatorStack.get(calculatorStack.size() - 1)));
    } else if (calculatorStack.size() >= 1) {
      firstStack.setText(
          String.valueOf(calculatorStack.get(calculatorStack.size() - 1)));
    } else {
      firstStack.setText("");
      secondStack.setText("");
    }
  }

  // Process the final result from the numbers and symbols stack.

  public int processResult() {

    int resultCount = 0;

    // Get the symbols and numbers from inside both stacks.

    for (int i = 0; i < calculatorStack.size() - 1; i += 2) {
      if (i == calculatorStack.size() - 1) {
        if (symbolStack.get(i).equals("+")) {
          resultCount += calculatorStack.get(i);
        } else if (symbolStack.get(i).equals("-")) {
          resultCount -= calculatorStack.get(i);
        } else if (symbolStack.get(i).equals("x")) {
          resultCount *= calculatorStack.get(i);
        } else if (symbolStack.get(i).equals("÷")) {
          resultCount /= calculatorStack.get(i);
        }
      } else {
        if ("+".equals(symbolStack.get(i))) {
          resultCount += (calculatorStack.get(i) + calculatorStack.get(i + 1));
        } else if ("-".equals(symbolStack.get(i))) {
          resultCount -= (calculatorStack.get(i) - calculatorStack.get(i + 1));
        } else if ("x".equals(symbolStack.get(i))) {
          resultCount += (calculatorStack.get(i) * calculatorStack.get(i + 1));
        } else if ("÷".equals(symbolStack.get(i))) {
          resultCount += (calculatorStack.get(i) / calculatorStack.get(i + 1));
        }
      }
    }

    // Clean calculator

    calculatorCount = "0";
    calculatorStack.clear();
    symbolStack.clear();

    // Return results and save as Ans.

    calculatorStack.add(resultCount * 1.0);
    symbolStack.add("+");
    return resultCount;
  }

  // Add commas to big numbers

  private String addCommas(Double number) {
    DecimalFormat numberFormatter = new DecimalFormat("###,###.###");
    return numberFormatter.format(number);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_calculator_pad, container, false);
  }
}