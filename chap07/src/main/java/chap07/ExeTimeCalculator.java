package chap07;

public class ExeTimeCalculator implements Calculator{

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }


    @Override
    public long factorial(long num) {
        long start = System.currentTimeMillis();
        long result = delegate.factorial(num);
        long end = System.currentTimeMillis();
        System.out.println(delegate.getClass().getSimpleName() + ".factorial(" +
                num + ") 실행 시간 = " + (end - start) + "\n");
        return result;
    }
}
