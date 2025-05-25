public class linkedListTest
{
    public static void main(String[] args) {
        islamLinkedList islam = new islamLinkedList();
        islamLinkedList islam2 = new islamLinkedList();
          islam2.addfirst(1);
          islam2.addfirst(2);
          islam2.addfirst(3);
//        islam.addfirst(4);
//        islam.addfirst(5);
//        islam.addfirst(6);
//        islam.addfirst(7);
//        islam.addfirst(8);
//        islam.addfirst(9);


        islam.addlast(1);
        islam.addlast(4);
        islam.addlast(16);
        islam.addlast(44);
        islam.addlast(1);
        islam.addlast(98);
        islam.addlast(3);
        islam.addlast(1);
        islam.addlast(9);



        islam.toStringprint();
       // islam2.toStringprint();
        System.out.println("_____________________");
        islam.reverse();
        //islam.deletList(islam2);

       // islam.toStringprint();

        islam.toStringprint();




    }}