# Java GUI 

## Student Management

- Here's the screenshot

![Screenshot from 2019-06-04 17-07-54](https://user-images.githubusercontent.com/44079366/58870970-06cef480-8676-11e9-897f-069182bf6a82.png)



- Here's the screenshot of updated one

![Screenshot from 2019-06-05 22-37-47](https://user-images.githubusercontent.com/44079366/58969902-475d6980-876d-11e9-8f23-b2cc31db5c26.png)


- Updated one with row filter 

![ezgif com-video-to-gif](https://user-images.githubusercontent.com/44079366/59034803-c2cc2300-8820-11e9-8e02-c38847f7ec2f.gif)

The issues: 
* This line of code only sort the first column as show below
```java
    rf = RowFilter.regexFilter(filter.getText(), 0);
```

## 06/07/2019 Update

* Turns out the problem is that if we remove the number it will sort all columns in the table 
```java
    rf = RowFilter.regexFilter(filter.getText());
```
- Here's the better one 

![ezgif com-video-to-gif](https://user-images.githubusercontent.com/44079366/59088679-05dbd400-88bd-11e9-9468-6f851abf62f8.gif)

## 06/10/2019 Update 

* First step into Desktop GUI

![zwhj6-wa8dd](https://user-images.githubusercontent.com/44079366/59180265-52bbe680-8b19-11e9-8703-7415b2e2baea.gif)

* Next progress

![Screenshot from 2019-06-10 19-22-57](https://user-images.githubusercontent.com/44079366/59195073-e2748b80-8b3f-11e9-8f13-9c184cf2bf64.png)

## 06/13/2019

* Added new button option *Export* which will export Jtable to Excel

![lx59s-ae30t](https://user-images.githubusercontent.com/44079366/59526836-cdc12c00-8e8e-11e9-830d-1eb77c0e134d.gif)


## 06/14/2019

* Added new history log which display the date/time of Inser, Edit and delete option

* Code for displaying current date/time: 


![8k3pm-xxat2](https://user-images.githubusercontent.com/44079366/59527050-68216f80-8e8f-11e9-8c3b-dd0b4a427618.gif)

* Here is the code for getting date/time
```java
    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())
```

# For Github Links use this 
- https://github.com/kynguyen98/Mr.-Phap-Final-