class Array<T> {
    private T[] array;
    private int size;
    
    @SuppressWarnings("unchecked")
    public Array(){
        array = (T[]) new Object[52];
        size = 0;
    }

    public void append(T value){
        array[size] = value;
        size++;
    }

    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        T oldVal = array[index];
        for (int i = index; i < size-1; i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--; 
        return oldVal;
    }

    public void remove(T value){
        int index = search(value);
        if (index == -1){
            return;
        }
        remove(index);
    }

    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public T set(int index, T value){
        T oldVal = get(index);

        array[index] = value;
        return oldVal;
    }

    public int search(T value){
        for (int i = 0; i < size; i++){
            if (array[i].equals(value)){
                return i;
            }
        }
        System.out.println("Card Not Found!");
        return -1;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++){
            sb.append(array[i]);
            if (i < size - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
