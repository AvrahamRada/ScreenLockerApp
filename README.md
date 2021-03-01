# ScreenLockerApp
Final project - mobile security course

[![](https://jitpack.io/v/AvrahamRada/RecyclerPickerDialogLibraryApp.svg)](https://jitpack.io/#AvrahamRada/RecyclerPickerDialogLibraryApp)

## Description:
```
My library create a dynamic dialog frame to the user.
the user just need to forward those information to the library:
 1. user's new title.
 2. type of dialog frame (0 - CheckBox, 1 - Radio Button and 2- switch)
 3. user's data (as array/list of strings).
 4. user's prefered color. 
```

## Setup:
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
          maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency:

```
dependencies {
      implementation 'com.github.AvrahamRada:RecyclerPickerDialogLibraryApp:1.00.02'
}


```
## Usage

![](assets/green.gif)      ![](assets/yellow.gif)      ![](assets/blue.gif)

:green_heart: To display green dialog with checkBox:
```java                    

String[] userData = {"Option -1-", "Option -2-", "Option -3-", "Option -4-", "Option -5-"}; // User data to display

MyOwnCustomDialog myFragment = new MyOwnCustomDialog("User Title", 0, userData, Color.argb(255, 153, 201, 99));
myFragment.show(getSupportFragmentManager(), "User TAG");

```

:yellow_heart: To display yellow dialog with RadioBtn:
```java                    

String[] userData = {"Option -1-", "Option -2-", "Option -3-", "Option -4-", "Option -5-"}; // User data to display

MyOwnCustomDialog myFragment = new MyOwnCustomDialog("User Title", 1, userData, Color.argb(255, 248, 229, 74));
myFragment.show(getSupportFragmentManager(), "User TAG");

```

:blue_heart: To display blue dialog with switch:
```java                    

String[] userData = {"Option -1-", "Option -2-", "Option -3-", "Option -4-", "Option -5-"}; // User data to display

MyOwnCustomDialog myFragment = new MyOwnCustomDialog("User Title", 2, userData, Color.argb(255, 124, 181, 189));
myFragment.show(getSupportFragmentManager(), "User TAG");

```

In order to get the answers/options when we click on 'SAVE' in the dialog, you need to add this to your code:

1. your class need to implements DialogInterface.OnDismissListener.
2. to add this method:

```java 
              @Override
              public void onDismiss(DialogInterface dialog) {
                   ArrayList<Integer> answers = myFragment.getMyAdapter().getYourAnswers();
                   Collections.sort(answers);

                   for (Integer answer : answers) {
                         Log.d("pttt", "answer #" + (answer + 1));
                         Log.d("pttt", "" + myFragment.getMyAdapter().getListOfData().get(answer));
                    }
               }
```
          
## License

    Copyright 2020 Avraham Rada

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

