** Note that this library is in early stages of development and not ready for production** 


# Android Wear Lift 

An Android library built for recording fitness/exercise data on Android Wear devices, for the moment mainly reps. Made extremely easy to drop and use on any Android project, our goal is to provide a module that can help Wear fitness app developers to easily and reliably handle about simple recording (and maybe one day, complex recording).

Contributors: Karanbir Randhawa@[](karanbirrandhawa) and Anthony Luu@[](anthonyluu)

Table of Contents
-----------------

- [History](#history)
- [Library](#Library)
- [Data](#Data)
- [Implementation](#Implementation)
- [Exercises](#Exercises)
- [Demo](#Demo)

## History

We started development for this application some time ago at a hackathon. At first it started off as a fitness tracker application that would accurately log your exercise statistics and allow you to analyze them. However this idea fell out some time after the hackathon. Still, impressed with the effort that we've made we decided to try to release our completed products one by one as libraries and build on them, hopefully to give other developers an easier chance to pick up where we left off. 

## Library

The repository itself can be imported immediately into Android Studio while taking into account that it consists of three different modules:

* `androidwearlift`
* `mobile`
* `wear`

Only the `androidwearlift` module is relevant to the library while the `mobile` and `wear` modules are to create a demo application to use. While importing you can choose to only import the `androidwearlift` library and with Gradle you can feel free to set it as one of your dependencies. 

## Data

Currently with our library you can record the following fitness data about the user:

* Reps
* Coming Soon

Our rep count is implemented via an internal state machine that will use a (somewhat) customized algorithm for each respective exercise within our supported exercises. 

## Implementation

Our goal is to make it extremely easy for you to get started on using the library. After adding the module `androidwearlift`

## Exercises

We have a number of exercises we plan to implement. Currently we are taking the safest and most accurate ones and putting them on there. These are the ones that we mainly tested at the hackathon: the bicep curl and the shoulder fly. The algorithm behind these two will be explored and explained within this readme at a future date.


## Demo

We've included a demo application that can be installed onto an Android Wear smartwatch (5.0-compliant) and test our algorithms our out for yourselves.
