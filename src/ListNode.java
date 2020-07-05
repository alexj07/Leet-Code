public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void print(){
        ListNode helper = this;
        while(helper != null){
            System.out.print(helper.val + "->");
            helper = helper.next;
        }
    }
}