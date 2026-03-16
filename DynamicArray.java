public class DynamicArray<T>{
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public DynamicArray(){
        array = (T[]) new Object[10];
        size = 0;
    }
    
    public void append(T value){
        if (size == array.length){
            grow();
        }

        array[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void grow(){
        T[] tmp = (T[]) new Object[array.length *2];

        for (int i = 0; i < array.length; i++){
            tmp[i] = array[i];
        }

        array = tmp;
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

        remove(index);
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

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++){
            sb.append(array[i]);
            if (i < size-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public int size(){
        return size;
    }
    
}
