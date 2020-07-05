
/*
Sort a linked list in O(n log n) time using constant space complexity.
 */

/*
Merge Sort:

 */

public class SortList {

    public ListNode sortList(ListNode list){
        if(list == null) return list;
        if(list.next == null) return list;

        ListNode[] halves = getHalves(list);
        ListNode left = halves[0],right = halves[1];
        left = sortList(left);
        right = sortList(right);
        return merge(left,right);
    }

    public ListNode[] getHalves(ListNode list) {
        ListNode[] halves = new ListNode[2];
        ListNode help = list;
        ListNode end = help;

        while(end.next.next != null && end.next.next.next != null){
            end = end.next.next;
            help = help.next;
        }
        halves[1] = help.next;
        help.next = null;
        halves[0] = list;

        return halves;
    }

    public ListNode merge(ListNode l1, ListNode l2){
        if(l1.val > l2.val) return merge(l2,l1);
        ListNode merged = l1;
        ListNode mergedHelp = merged;
        l1 = l1.next;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                mergedHelp.next = l1;
                mergedHelp = mergedHelp.next;
                l1 = l1.next;
            }else{
                mergedHelp.next = l2;
                mergedHelp = mergedHelp.next;
                l2 = l2.next;
            }
        }
        if(l1 != null) mergedHelp.next = l1;
        if(l2 != null) mergedHelp.next = l2;
        return merged;
    }

    public static void main(String[] args){
        ListNode tester = new ListNode(9);
        ListNode helper = tester;
        int[] values = {1,8,3,5,6,3,2,9};
        for(int val:values){
            helper.next = new ListNode(val);
            helper = helper.next;
        }
        SortList test = new SortList();

        ListNode answer = test.sortList(tester);
        answer.print();
    }
}
