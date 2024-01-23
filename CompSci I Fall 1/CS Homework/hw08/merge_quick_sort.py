"""
Nick Salvemini
HW 08
Hybrid sort module
"""

import merge_sort
import quick_sort

def merge_quick_sort(list):
    """
    Utilizes a hybrid sort to sort a list
    Parameters
        list: List to be sorted
    Returns
        Returns the sorted list
    """
    if list == []:
        return []
    elif len(list) == 1:
        return list
    else:
        left, right = merge_sort.split(list)
        leftLess, leftSame, leftMore = quick_sort.partition(left[len(left) // 2], left)
        rightLess, rightSame, rightMore = quick_sort.partition(right[len(right) // 2], right)
        left = merge_quick_sort(leftLess) + leftSame + merge_quick_sort(leftMore)
        right = merge_quick_sort(rightLess) + rightSame + merge_quick_sort(rightMore)
        return merge_sort.merge(left, right)