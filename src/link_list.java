import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class link_list {


//    public ListNode oddEvenList(ListNode head) {
//        ListNode head_tmp = new ListNode(0);
//        ListNode head_tmp1 = new ListNode(0);
//
//        ListNode begin = new ListNode(0);
//        ListNode begin1 = new ListNode(0);
//
//        ListNode end = new ListNode(0);
//        ListNode end1 = new ListNode(0);
//
//        begin.next = end;
//        begin1.next = end1;
//
//        int i = 0;
//        while (head != null) {
//            if (i % 2 == 0) {
//                end.next = head;
//                end = head;
//            } else {
//                end1.next = head;
//                end1 = head;
//            }
//            ++i;
//            head = head.next;
//        }
//        end.next = begin1.next.next;
//
//        return begin.next.next;
//
//    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;

        ListNode begin = head;
        ListNode begin1 = head.next;

        ListNode end = begin;
        ListNode end1 = head.next;

        int i = 0;

        head = end1.next;
        while (head != null) {
            if (i % 2 == 0) {
                end.next = head;
                end = head;
            } else {
                end1.next = head;
                end1 = head;
            }
            ++i;
            head = head.next;
        }

        end.next = begin1;
        end1.next = null;

        return begin;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode begin = headA;
        ListNode begin1 = headB;

        int A = 0;
        int B = 0;

        while (begin != null) {
            ++A;
            if (begin.next == null)
                break;

            begin = begin.next;
        }
        while (begin1 != null) {
            ++B;
            if (begin1.next == null)
                break;
            begin1 = begin1.next;


        }

        if (begin1.val != begin.val)
            return null;

        begin = headA;
        begin1 = headB;
        if (A > B) {
            A = A - B;
            for (int i = 0; i < A; i++) {
                begin = begin.next;
            }
        } else {
            B = B - A;
            for (int i = 0; i < B; i++) {
                begin1 = begin1.next;
            }
        }

        while (begin.val != begin1.val) {
            begin = begin.next;
            begin1 = begin1.next;
        }


        return begin;

    }


    public static void main(String[] args) {
        int[] tmp = new int[]{1, 3, 5, 6, 7, 8, 9, 10};
        ListNode begin = new ListNode(tmp[0]);
        ListNode end = begin;
        for (int i = 1; i < tmp.length; i++) {
            ListNode tmpp = new ListNode(tmp[i]);
            end.next = tmpp;
            end = tmpp;
        }

        int[] tmp1 = new int[]{2, 4, 6, 7, 8, 9, 10};
        ListNode begin1 = new ListNode(tmp1[0]);
        ListNode end1 = begin1;
        for (int i = 1; i < tmp1.length; i++) {
            ListNode tmpp1 = new ListNode(tmp1[i]);
            end1.next = tmpp1;
            end1 = tmpp1;
        }

        getIntersectionNode(begin, begin1);
    }


}