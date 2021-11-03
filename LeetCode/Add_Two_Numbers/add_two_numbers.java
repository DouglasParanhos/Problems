// Url: https://leetcode.com/problems/add-two-numbers/
// Difficulty: Medium

/**
* You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
*
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        boolean carryTemp;
        ListNode nodeL1 = l1;
        ListNode nodeL2 = l2;
        ListNode result = null;
        ListNode resultIterator1 = null;
        ListNode resultIterator2 = null;
        
        while(nodeL1 != null || nodeL2 != null) {
            int sum = (nodeL1 != null ? nodeL1.val : 0) + (nodeL2 != null ? nodeL2.val : 0) + carry;
            carryTemp = sum > 9;
            
            if(carryTemp) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }
            
            if(result == null){
                result = new ListNode(sum);
            } else {
                if(resultIterator1 == null) {
                    resultIterator1 = new ListNode(sum);
                    result.next = resultIterator1;
                } else {
                    resultIterator2 = new ListNode(sum);
                    resultIterator1.next = resultIterator2;
                    resultIterator1 = resultIterator1.next;
                }
                
            }
            
            carryTemp = false;
            nodeL1 = nodeL1 != null ? nodeL1.next : null;
            nodeL2 = nodeL2 != null ? nodeL2.next : null;
        }
        
        if(carry > 0) {
            ListNode lastNode = new ListNode(1);
            
            if(resultIterator1 == null) {
                result.next = lastNode;
            } else {
                resultIterator1.next = lastNode;
            }
        }
             
        return result;
    }
}

/**
* Runtime: 2 ms, faster than 74.02% of Java online submissions for Add Two Numbers.
* Memory Usage: 39.3 MB, less than 76.69% of Java online submissions for Add Two Numbers.
*/