public class islamStacks {
    islamArray<Object> list =  new islamArray();

    public void push(Object item) {

        list.addlest(item);

    }
    public Object pop() {
       return list.removeLast();
    }

    public Object peek() {
        return list.get(list.size-1);
    }





}
