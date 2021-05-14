package ltd.egoist.sellTicket;

/**
 * @Classname SellTicket
 * @Description TODO
 * @Date 2021/5/1 21:33
 * @Created by Lenovo
 */
class Ticket {
    private int num = 30;

    public synchronized void sellTicketToPeople() {
        if(num > 0){
            System.out.println(Thread.currentThread().getName() + "\t卖出了第:" + (num--) + "张票" + "\t还剩下:" + num + "张");
        }
    }
}

public class SellTicket {
    /**
     * 多线程资源编写套路 线程   操作   资源类
     */
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30 ; i++){
                    ticket.sellTicketToPeople();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30 ; i++){
                    ticket.sellTicketToPeople();
                }
            }
        }, "B").start();

    }
}
