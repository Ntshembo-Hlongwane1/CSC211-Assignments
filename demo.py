def quick_sort(lst):
    if lst == []:   return []
    pivot = lst[0]
    left = [x for x in lst[1:] if x < pivot]
    right = [x for x in lst[1:] if x > pivot]
    print(lst, pivot, "is pivot", left, "and", right, "should be sorted")
    new_list = quick_sort(left) + [pivot] + quick_sort(right)
    print("NEW", new_list)
    return new_list

quick_sort([72, 77, 82, 87, 92, 67, 62, 57, 50, 75 ])