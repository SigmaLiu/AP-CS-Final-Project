import java.util.Arrays;
import java.util.Scanner;

public class FinalProject
{
    private static int[][] a;
    private static int s, sz;

    /**
     * create empty board with size size*size
     * @param size
     */
    public FinalProject(int size)
    {
        s=0;
        sz=size;
        a=new int[sz][sz];
    }

    /**
     * generate new block
     */
    public static void generate()
    {
        int r=(int)(Math.random()*sz*sz);
        while(a[r/sz][r%sz]!=0)
            r=(int)(Math.random()*sz*sz);
        int v=(int)(Math.random()*10);
        if(v==0)
            a[r/sz][r%sz]=4;
        else
            a[r/sz][r%sz]=2;
        return;
    }

    /**
     * move in the direction of x
     * @param x
     */
    public static void move(char x)
    {
        if(x=='w')
            for(int j=0; j<sz; j++)
            {
                int[] t=new int[sz];
                int cnt=0;
                for(int i=0; i<sz; i++)
                    if(a[i][j]!=0)
                        t[cnt++]=a[i][j];
                for(int k=0; k<cnt-1; k++)
                    if(t[k]==t[k+1])
                    {
                        t[k]*=2;
                        t[k+1]=0;
                        s+=t[k];
                    }
                for(int k=0; k<sz; k++)
                    a[k][j]=0;
                cnt=0;
                for(int k=0; k<sz; k++)
                    if(t[k]!=0)
                        a[cnt++][j]=t[k];
            }
        if(x=='a')
            for(int i=0; i<sz; i++)
            {
                int[] t=new int[sz];
                int cnt=0;
                for(int j=0; j<sz; j++)
                    if(a[i][j]!=0)
                        t[cnt++]=a[i][j];
                for(int k=0; k<cnt-1; k++)
                    if(t[k]==t[k+1])
                    {
                        t[k]*=2;
                        t[k+1]=0;
                        s+=t[k];
                    }
                for(int k=0; k<sz; k++)
                    a[i][k]=0;
                cnt=0;
                for(int k=0; k<sz; k++)
                    if(t[k]!=0)
                        a[i][cnt++]=t[k];
                }
        if(x=='s')
            for(int j=0; j<sz; j++)
            {
                int[] t=new int[sz];
                int cnt=0;
                for(int i=sz-1; i>=0; i--)
                    if(a[i][j]!=0)
                        t[cnt++]=a[i][j];
                for(int k=0; k<cnt-1; k++)
                    if(t[k]==t[k+1])
                    {
                        t[k]*=2;
                        t[k+1]=0;
                        s+=t[k];
                    }
                for(int k=0; k<sz; k++)
                    a[k][j]=0;
                cnt=sz-1;
                for(int k=0; k<sz; k++)
                    if(t[k]!=0)
                        a[cnt--][j]=t[k];
            }
        if(x=='d')
            for(int i=0; i<sz; i++)
            {
                int[] t=new int[sz];
                int cnt=0;
                for(int j=sz-1; j>=0; j--)
                    if(a[i][j]!=0)
                        t[cnt++]=a[i][j];
                for(int k=0; k<cnt-1; k++)
                    if(t[k]==t[k+1])
                    {
                        t[k]*=2;
                        t[k+1]=0;
                        s+=t[k];
                    }
                for(int k=0; k<sz; k++)
                    a[i][k]=0;
                cnt=sz-1;
                for(int k=0; k<sz; k++)
                    if(t[k]!=0)
                        a[i][cnt--]=t[k];
            }
        return;
    }
    public static boolean checkFail()
    {
        for(int i=0; i<sz; i++)
            for(int j=0; j<sz; j++)
                if(a[i][j]==0)
                    return false;
        for(int i=0; i<sz-1; i++)
            for(int j=0; j<sz; j++)
                if(a[i][j]==a[i+1][j])
                    return false;
        for(int i=0; i<sz; i++)
            for(int j=0; j<sz-1; j++)
                if(a[i][j]==a[i][j+1])
                    return false;
        return true;
    }

    /**
     *
     * @return current board
     */
    public String toString()
    {
        String s="";
        for(int i=0; i<sz; i++)
        {
            for(int j=0; j<sz; j++)
            {
                s+=a[i][j];
                for(int k=0; k<8-Math.log10(a[i][j]+1); k++)
                    s+=" ";
            }
            s+='\n';
        }
        return s;
    }

    public static int getS() {return s;}

    public static int[][] getA() {return a;}

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        FinalProject n=new FinalProject(in.nextInt());
        n.generate();
        while(!n.checkFail())
        {
            boolean c=false;
            int[][] p=new int[sz][sz];
            int[][] p1=getA();
            for(int i=0; i<sz; i++)
                for(int j=0; j<sz; j++)
                    p[i][j]=p1[i][j];
            System.out.println(getS());
            System.out.print(n);
            n.move(in.next().charAt(0));
            p1=getA();
            for(int i=0; i<sz; i++)
                for(int j=0; j<sz; j++)
                    if(p[i][j]!=p1[i][j])
                        c=true;
            if(c)
                n.generate();
        }
        System.out.print(n);
        System.out.println("Game ended: you scored "+n.getS()+" points");
    }
}
