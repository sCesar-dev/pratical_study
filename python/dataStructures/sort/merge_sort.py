def merge_sort(arr, left, right):
    
    if left < right:
        middle = (left + right) // 2
        merge_sort(arr, left, middle)
        merge_sort(arr, middle+1, right)
        merge(arr, left, middle, right)
        
    return arr
        
    
def merge(arr, left, middle, right):
    result = []
    i = left
    j = middle + 1
    k = 0
    
    while i <= middle and j <= right:
        if arr[i] < arr[j]:
            result[k:k+1] = arr[i:i+1]
        else:
            result[k:k+1] = arr[j:j+1]
            
    while i <= middle:
        result[k:k+1] = arr[i:i+1]
    
    while j < right:
        result[k:k+1] = arr[j:j+1]
        
    for x in arr:
        arr[x] = result[x]
        
arr2 = [10, 5, 2, -2, 20, 10, 0, 7]
merge_sort(arr2, 0, len(arr2))
print(arr2)
