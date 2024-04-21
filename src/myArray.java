public class myArray implements Array{
    int[] a = new int[50];
    int size = 0;
    @Override
    public int GET(int i) {
        return a[i];
    }

    @Override
    public boolean ADD(int val) {
        if(size != a.length){
            a[size] = val;
            size++;
            return true;
        }
        else {
            return false;
        }
    }
}
