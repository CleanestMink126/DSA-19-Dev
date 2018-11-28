## Text Justification

Given a body of text as an array of strings in order (`w`) and the maximum length (`m`) of a line, return a `List` of the indices of the first word of each line of justified text.  For example: 

```
w = {"This", "is", "an", "example", "of", "text", "justification"}
m = 16

This   is    an 
example of text
justification
```

should return the array `{0, 3, 6}`.

**Reminders from class:**

The cost of line l is c(l):
* c(l) = INF, if total_length(l) > m
* c(l) = (m - total_length(l))^3, otherwise

where:
* total_length = words + single space between each word (i.e. the number of words in the line - 1)
* m = the maximum length of a line