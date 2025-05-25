
public class islamLinkedList {

    private islamNode head;
    private islamNode tail;
    private int size = 0;

    public islamLinkedList() {
    }

    public void addlast(Object e) {
        islamNode newnode = new islamNode(e);
        if (tail == null) {
            tail = head = newnode;
        } else {
            tail.next = newnode;
            tail = newnode;
        }
        size++;
    }

    public void addfirst(Object e) {
        islamNode newnode = new islamNode(e);
        newnode.next = head;
        head = newnode;
        size++;

        if (tail == null) {
            tail = head;
        }
    }


    public void addIndex(int index, Object e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            addfirst(e);
        } else if (index == size) {
            addlast(e);
        } else {
            islamNode newnode = new islamNode(e);
            islamNode pointer = head;
            for (int i = 0; i < index - 1; i++) {
                pointer = pointer.next;
            }
            newnode.next = pointer.next;
            pointer.next = newnode;
            size++;
        }
    }


    public Object removefirst() {
        if (size == 0) {
            return null;
        } else {
            islamNode temp = head;
            head = head.next;

        }
        if (head == null) {
            tail = null;
        }
        size--;
        return head;
    }

    public Object removeLast() {
        islamNode temp = tail;

        if (size == 0) {
            return null;
        }
        if (size == 1) {
            head = tail = null;
            size = 0;
            return temp;
        } else {
            temp = head;
            islamNode pointer = head;
            for (int i = 0; i < size - 2; i++) {
                pointer = pointer.next;
            }
            pointer.next = null;
            tail = pointer;
            size--;
            return temp.data;

        }
    }

    public Object removeIndex(int index) {
        islamNode pointer = head;
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return removefirst();
        } else {
            if (index == size - 1) {
                return removeLast();
            } else {
                for (int i = 0; i < index - 1; i++) {
                    pointer = pointer.next;
                }
                islamNode temp = pointer.next;
                pointer.next = pointer.next.next;

                size--;
                return temp.data;
            }
        }
    }

    public Object getIndex(int index) {
        islamNode pointer = head;
        if (index < 0 || index >= size) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;

    }

    public void toStringprint() {

        islamNode pointer = head;


        System.out.println("[");
        while (pointer != null) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.println("]");
    }

    public void deletThecc() {
        islamNode temp = head;
        islamNode prev = null;
        while (temp.next != null) {
            if (temp.data.equals(temp.next.data) && temp == head) {
                head = head.next;
                temp.next = null;
            }

            if (temp.next.data == temp.data) {
                prev.next = temp.next;
                temp = temp.next;
            } else {
                prev = temp;
                temp = temp.next;
            }

        }
    }

    public void removeElem(int x) {
        islamNode temp = head;
        int z = size - x - 1;
        while (temp != null && z > 0) {
            temp = temp.next;
            z--;

        }
        temp.next = null;


    }

    public void removeFirstElem(int x) {
        islamNode temp = head;


        while (temp != null && x > 1) {
            temp = temp.next;
            x--;
        }
        head = temp.next;
        temp.next = null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

//    public void deletList(islamLinkedList x){
//        islamNode temp = head;
//        int index = 0;
//        boolean flag=false;
//        while (temp != null) {
//            flag=false;
//            for (int i = 0; i < x.size; i++) {
//                if (temp.data.equals(x.getIndex(i))) {
//                    if (temp == head) {
//                        removefirst();
//                        temp = head;
//                    }
//                    else {
//                        temp=temp.next;
//                        removeIndex(index);
//                    }
//                    flag=true;
//                    break;
//
//
//                }
//
//            }
//                index++;
//                if (flag == false) {
//                    temp = temp.next;
//
//            }
//        }

    public void deletList(islamLinkedList x) {
        islamNode temp = head;
        islamNode prev = null;

        while (temp != null) {
            boolean found = false;

            for (int i = 0; i < x.size; i++) {
                if (temp.data.equals(x.getIndex(i))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                if (temp == head) {
                    head = head.next;
                    temp = head;
                } else {
                    prev.next = temp.next;
                    temp = temp.next;
                }
                size--;
            } else {
                prev = temp;
                temp = temp.next;
            }
        }

        // تحديث tail إذا القائمة أصبحت فاضية
        if (head == null) {
            tail = null;
        }
    }

    public void printREV(islamNode n) {
        if (n == null) {
            return;
        }
        printREV(n.next);
        System.out.print(n.data + " ");

    }

    public void printREV() {
        printREV(head);

    } // HELPER METHODS

    public void reverse(islamNode pre, islamNode curr) {
        if (curr == null) {
            head = pre;
            return;
        }
        reverse(pre.next, curr.next);
        curr.next = pre;
        pre.next = null;

    }

    public void reverse() {
        reverse(head, head.next);

    }

}












