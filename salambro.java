package project2;

import java.util.*;

//operation 0 menu
//operation 1 submenu
//operation 2 price

public class salambro {

    //main
    public static void main(String[] args) {
        salambro menu = new salambro();
        menu.show();
    }

    //global var
    public Scanner input;
    public String[] panel, instruction;
    public ArrayList<LinkedList<LinkedList<String>>> menu;
    public String empty;
    //     menu1 {{menu1, submenu}, {price, subprice}}
    //     menu2 {{menu2, submenu}, {price, subprice}}

    //constructor
    public salambro() {


        input = new Scanner(System.in);


        panel = new String[]{
                "Exit",
                "Change Welcome Message",
                "Change Menu",
                "Change Submenu",
                "Change Prices",
                "Display Welcome Message",
                "Display Menu",
                "Display Submenu",
                "Display Prices",
                "Switch to Client Mode (coming soon)",
                "Welcome hui!\n"
        };

        instruction = new String[]{
                "Please, type the message that clients will see when they enter the menu:\nTip: use underscore (_) as a splitter\n",
                "Here you can od the following activities:\n0. Go Back\n1. Add %s.\n2. Remove %s.\n",
                "Write the names of %s separated by comma (c1, c2, ...):\n",
                "Choose %s you want to %s:\n",
                "Tip: write the indexes separated by comma (i1, i2, ...)\n",
                "Enter a new price for %s:\n"

        };

        empty = "You don't have a %s\n\n";

        menu = new ArrayList<>();


        System.out.println(panel[panel.length - 1]);

    }

    //menu
    public void show() {

        int i = 0;
        for (; i < panel.length - 1; i++) {
            System.out.printf("%d. %s.\n", i, panel[i]);
        }
        int option = input.nextInt();
        choose(option);
    }

    //choose
    public void choose(int option) {
        if (option != 0)
            System.out.printf("Choose an activity: %d\n\n", option);

        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                first();
                break;
            case 2:
                second();
                break;
            case 3:
                third();
                break;
            case 4:
                fourth();
                break;
            case 5:
                fifth();
                break;
            case 6:
                sixth();
                break;
            case 7:
                seventh();
                break;
            case 8:
                eighth();
                break;
            case 9:
                break;
            default:
                show();
        }
    }

    //select
    public void select(int operation, String type, String str) {
        int option;

        if (operation == 2) option = 1;

        else {
            System.out.printf(instruction[1], str, str);
            option = input.nextInt();
            System.out.printf("Your choice: %d\n\n", option);
        }

        switch (option) {
            case 0:
                break;
            case 1:
                add(operation, type);
                break;
            case 2:
                remove(operation, type);
                break;
            default:
                select(operation, type, str);
        }
    }

    //options

    public void first() {
        System.out.println(instruction[0]);
        String welcome = input.next() + input.nextLine();
        welcome = welcome.replace('_', '\n');
        panel[panel.length - 1] = welcome;

        System.out.println();
        show();
    }

    public void second() {

        select(0, "categories", "category");

        show();
    }

    private void third() {

        select(1, "subcategories", "subcategory");

        show();
    }

    private void fourth() {
        select(2, "prices", "price");
        show();
    }

    public void fifth() {
        System.out.println(panel[10]);
        show();
    }

    public void sixth() {
        display(0, "menu");
    }

    public void seventh() {
        display(1, "subcategories");
    }

    public void eighth() {
        display(2, "prices");
    }


    public void add(int operation, String str) {

        int index = 0;
        String from = "", to = "";

        if (operation == 1 || operation == 0)
        {
            from = "a category for which";
            to = "add " + str;
        }
        if (operation == 2)
        {
            from = str + " for which";
            to = "change prices";
        }
        //for operations 1 2
        if (operation != 0)
        {
            //check is menu empty
            if (menu.isEmpty())
            {
                System.out.printf(empty, str + " in this category.");
                show();
            }
            //if not empty
            System.out.printf(instruction[3], from, to);
            //sout categories for choosing
            list();
            //index of category
            index = input.nextInt();
        }
        //for operation 0 1
        if (operation != 2)
            System.out.printf(instruction[2], str);

        if (operation == 2) {
            sublist(index, "price");
            System.out.println(instruction[4]);
        }

        //input + ","
        String menu = input.next() + input.nextLine() + ",";



        ArrayList<String> l = slice(menu);

        if (operation == 2)
        {
            for (String x : l) {
                int i = Integer.parseInt(x);

                if (i <= this.menu.get(index-1).get(0).size())
                {
                    System.out.printf(instruction[5], this.menu.get(index-1).get(0).get(i));

                    this.menu.get(index-1).get(1).set(i, input.next()+input.nextLine());

//                    System.out.println(this.menu.get(index-1).get(1));
                }
            }
            System.out.println();
            show();
        }

        int t = 0;
        for (String sub : l) {//until l.length()

            //menu
            if (operation == 0)
            {
                LinkedList<LinkedList<String>> lists = new LinkedList<>();
                lists.add(new LinkedList<>());
                lists.add(new LinkedList<>());
                this.menu.add(lists);
                this.menu.get(t).get(0).add(sub);
                this.menu.get(t).get(1).add("prices");


            }

            //submenu
            if (operation == 1)
            {
                this.menu.get(index-1).get(0).add(sub);
                this.menu.get(index-1).get(1).add("0");
            }
            t++;
        }


//        System.out.println(this.menu);  // tip: select line & type (ctrl) + (.)   check, run!
        System.out.println();
        show();
    }

    public void remove(int operation, String str) {
        if (menu.isEmpty()) {
            System.out.printf(empty, str);
            show();
        }

        int index;
        String indexes = "";
        String from = "", to = "";

        if (operation != 2)
        {
            from = "the category from which";
            to = "remove " + str;
        }

        if (operation == 2)
        {
            from = str + " for which";
            to = "change prices";
        }

        System.out.printf(instruction[3], from, to);

        list();



        index = input.nextInt()-1;

        if (menu.get(index).get(0).size() < 2) {
            System.out.printf(empty, str);
            show();
        }

        //for operations 1 2
        if (operation != 0)
        {
            System.out.printf(instruction[3], str, " remove by its index shown below");

            sublist(index, "");

            System.out.println(instruction[4]);

            indexes = input.next()+input.nextLine() + ",";
        }


        //l
        ArrayList<String> l = slice(indexes);

        for (int o = 0,p = 0; o < menu.size(); o++)
        {
            if (operation != 0)
            for (LinkedList<String> list : menu.get(o))
            {
                if (operation == 2 && p == 0){ p++; continue;}
                if (operation != 2 && p == 1) break;

                int u = 0;
                for (String x: l)
                {
                    int k = Integer.parseInt(x);
                    k-=u;
                    if (k >= list.size()) continue;

                    if (p != 2)
                        list.remove(x);
                    else
                        list.set(k, "0");
                    u++;

                }
                p++;
            }
            else {
                menu.remove(menu.get(index));
                break;
            }
        }
//        System.out.println(menu);
        System.out.println();
        show();
    }

    public void display(int option, String str) {
        if (menu.isEmpty()) {
            System.out.printf(empty, str + " yet\n");
            show();
        }

        String price = "";
        if (option == 1)
            System.out.printf(instruction[3], "category for which", "see its " + str);
        if (option == 2)
            price = "price";

            list();

        if (option != 0) {
            int choose = input.nextInt()-1;

            if (menu.get(choose).get(0).size() > 1) {
                sublist(choose, price);
            }
            else
                System.out.printf(empty, str + " in this category");
        }
        System.out.println();
        show();

    }

    public void list() {

        for (int i = 0; i < menu.size(); i++) {
            String list = menu.get(i).get(0).get(0);
            System.out.printf("%d. %s.\n", i + 1, list);
        }
        System.out.println();
    }

    public void sublist(int index, String price) {
        boolean isprice = price.equals("price");


        for (int j = 1; j < menu.get(index).get(0).size(); j++) {
            if (isprice) price = " - "+menu.get(index).get(1).get(j)+" kzt";
            System.out.printf("%d. %s.\n", j, menu.get(index).get(0).get(j) + price);
        }
        System.out.println();
    }

    public ArrayList<String> slice(String str) {
        ArrayList<String> list = new ArrayList<>();

        int k = 0, j = 0;

        for (char x : str.toCharArray())
        {
            if (x == ','){
                //slicing particle, that is an element
                String sub = str.substring(j, k);


                list.add(sub);

                //starting index after slice
                j += sub.length() + 2;
            }
            k++;
        }
        return list;
    }
}

