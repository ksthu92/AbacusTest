# Abacus Test
Recycler View Items with FireStore

## Table of Contents  
[Versions](#Versions)

[Synopsis](#Synopsis)

[Third party libraries](#third-party-libraries)

[Installation](#Installation)

[Testing](#testing)

[Author](#author)

## Versions

* Android Studio v3.4
* Android Sdk v26.1.1
* Android Build Tools v28.0.3

## Synopsis

* Added Recycler View to display colour items from JSON raw file.
* Firestore Implementation to save colour items to Cloud Firestore.
* Binding Views with ButterKnife.

## Third party libraries

*  Butter Knife Library
*  FirebaseFirestore

## Installation

* Click Clone or download button to download as Zip file.
* Extract the zip file and open Android studio to open the project.
* Wait for the project to finish building.

![Untitled](https://user-images.githubusercontent.com/49223246/56940143-dadcc280-6b4f-11e9-8676-f41ee32e435d.png)

* Check your firebase is connected by clicking tools and select firebase

![Capture](https://user-images.githubusercontent.com/49223246/56940656-1927b100-6b53-11e9-86da-cc5f83e3afbe.PNG)

* If the firebase has not set up in the android studio, Please follow the instructions from 
 [https://firebase.google.com/docs/firestore/quickstart](https://firebase.google.com/docs/firestore/quickstart)


## Testing

* When the application runs offline(without Internet), onFailureListener does not trigger since it only triggers when the data is unable to write in database.
* However, Cloud firestore saves the data as cache when offline and it synchronizes any local changes back to Cloud Firestore.


## Author
* Kyaw Si Thu
