# README

## Metaculous GWT Project Scaffolding Framework

### Introduction
Metaculous is a framework for scaffolding and building applications with GWT and currently supports the Ext-GWT UI component library (see third-party).  Some of the features of this framework are as follows.

* Provides the plumbing to perform GWT-RPC calls via the new GWT event bus API which is useful for UI development as those objects that are interested in data events can subscribe to remote call event bus topics on a per-RPC-call basis. 

* Provides a set of patterns for sharing UI code across projects with minimal bootstrapping code.

* One can optionally use metadata stored in a database (or other persistent store) to assemble common, data-bound, UI patterns.  Such as List, Search, Edit, etc.

We hope that you will consider this project if you need to get a Rich Internet Application (RIA) running quickly and will have to manage large sets of requirements. (see Target Audience)

### History
Metaculous is inspired by an existing framework developed by Alberto Artasanchez known as QuickWidgets (QW).  QW allowed for one to develop an Ext-GWT based UI via metadata entries in a database.  The metadata was bound at runtime to describe entity relationships between common UI patterns such as search, list, advanced search, and edit as well as how they bound to their data sources.  QW was similar to Ruby on Rails in many respects except it was a rich client framework and the "model" objects were declared in the database (not code).

### Motivation
The motivation behind this project was to fork the QW framework mentioned previously and uplift the codebase to use the GWT Event Bus.  Additionally the goal was to provide a more flexible runtime where one can pick abstractions "ala-carte."  In short, the motivation behind this project was to allow one to quickly scaffold their project with common patterns related to data binding.  This project would be deemed a success by its authors if one can have enough abstraction to keep the project cadence going but free of enough clutter and distractions than starting from scratch.  

### Target Audience
This framework is targeted for anyone charged with developing and owning a large set of features with the Ext-GWT technology stack.  This means you are converting a legacy client-server desktop application or are developing a front-office type RIA. It would probably be too much abstraction for a very simple project or a web portal type application (depending on your sense of order :-).

### In-Progress
The following features are under construction or are high priority features.

* Currently the metadata feature is not complete and is still under construction.  Details will be forthcoming.

* Multiple "Showcase" projects demonstrating various aspects of the framework.

* Make the README less buzz-word compliant.

* Maven integration (only ant and Eclipse is supported)

* Provide support for other GWT UI component libraries (i.e. Smart-GWT)

* Add usage instructions to this README.

### Prerequisites
This project assumes you are developing with Eclipse, Google's GWT Plugin for Eclipse, and Ant.

### Third Party
Google Web Toolkit (GWT)

* [GWT Home Page] (http://code.google.com/webtoolkit/ "GWT Homepage")
Ext-GWT is a ui component library developed and licensed by Sencha.
[Sencha Ext-GWT Homepage] (http://www.sencha.com/Ext-GWT "Sencha Ext-GWT Homepage")

* Alberto Artasanchez
[Alberto's Linked-In Page](http://www.linkedin.com/in/artasanchez "Alberto Artasanchez")