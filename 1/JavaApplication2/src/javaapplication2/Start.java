class Start {

    public static final double A0 = -3.5;
    public static final double B0 = -7.5;
    public static final double R_A = -0.75;
    public static final double R_B = 1.24975;
    public static final int N = 8;
    
    public static void main(String[] args) {
                
        
        //A[0]=10	B[0]=15 A[0]<B[0]
        
        
        for(int i=0;i<=N;i++)
        {
           System.out.print("A[" + i +"]=" + (A0 + (R_A * i)) + " " + "B[" + i +"]=" + (B0 + (R_B * i)) + " " );
           if(Math.abs((B0  + (R_B * i) ) - (A0 + (R_A * i))) < 0.001)
           {
               System.out.println("A[" + i + "]=B[" + i +"]");
           }
           else if(Math.abs((B0  + (R_B * i) ) - (A0 + (R_A * i))) >= 0.001 && B0  + (R_B * i) > A0 + (R_A * i))
           {
               System.out.println("A[" + i + "]<B[" + i +"]");
           }
           else if(Math.abs((B0  + (R_B * i) ) - (A0 + (R_A * i))) >= 0.001 && B0  + (R_B * i) < A0 + (R_A * i))
           {
               System.out.println("A[" + i + "]>B[" + i +"]");
           }
           
        }
        
        
        
        
    }
    
}
