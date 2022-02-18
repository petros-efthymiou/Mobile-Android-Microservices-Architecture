Mobile Android Microservices Architecture
=========================================
Introduction
------------
This repo is an example of the Mobile Microservices Architecture and is offered as an appendix to the book <INSERT_BOOK_NAME_AND_LINK>. The book is covering all the aspects of building Clean Mobile Applications and explains the reasoning behind all the decisions made in this sample project.

This architecture is primarily intended for educational purposes and intends to describe engineering concepts and practices such as:

* Clean architecture
* Inversion of Control
  * Dependency Inversion
  * Dependency Injection
* S.O.L.I.D principles
* MVI
* Reactive Programming
* Unit testing
* UI Testing
* Segregation into independent modules
* Packaging

The philosophy behind the Mobile Microservices Architecture is to segrate the codebase into multiple independent modules that can be developed in complete isolation from the rest. Clean Architecture and the Dependency rule apply. High level modules are completely agnostic of the low level ones. 

It can be used as a base for an enterprise production app where several independent teams are supposed to co-develop a large scale, complex application in parallel. The boundaries can enable each team to make its own choices on architecture, code style and usage of third party libraries. It's not suited for simple or medium complexity apps as its boundaries will essentially serve as impediments to a small team of developers. Furthermore, independent modules also effectively push against consistency in the codebase. It's a tradeoff similar to Microservices in a backend system. Each Microservice can use it's own Programming language and architecture but it is also adding overhead.

For a more Pragmatic approach that can be used in projects of every size, you can refer to the other example included in the book [Pragmatic Clean Architecture](https://github.com/petros-efthymiou/Android-Pragmatic-Clean-Architecture).

Clean Layering
--------------

![MicroservicesLayering](https://user-images.githubusercontent.com/98778003/154696142-dfd55b92-6578-435f-ae4c-d4597ef9ffe4.png)

The diagram above describes the layering of the project. The thick lines indicate hard boundaries, separation in modules while the thin lines represent soft boundaries, separation in packages.

Architectural Overview
---------------
![Copy of Copy of Untitled Diagram drawio (1) (14)](https://user-images.githubusercontent.com/98778003/154697780-09d210b9-61a0-4f55-ae45-0570b4b3edd9.png)

For more information please refer to the book.

License
--------
```
  Copyright (C) 2022 Petros Efthymiou Open Source Project

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ```

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/petrosefth)

