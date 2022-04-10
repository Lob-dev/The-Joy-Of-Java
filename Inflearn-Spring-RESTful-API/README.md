# Inflearn-Spring-RESTful-API
백기선님의 RESTful API를 마음대로 따라하는 Repo 입니다.

Spring boot 2.1.0 기준으로 작성된 강의를 2.4.4 버전으로 변경하였고, JDK 기능을 더 사용하거나 하진 않았지만 8버전에서 11버전으로 올렸습니다.  <br/>

 <br/>

객체 변환 간에 사용되었던 ModelMapper는 MapStruct로 변경하였고, 버전 변경으로 미지원되는 기능들에 대해선 버전에 맞는 API를 사용하려고 노력하며 강의를 듣고 있습니다.  <br/>

 <br/>

MapStruct 버전은 1.4.2.Final Lombok 버전은 1.18.12이며, Lombok을 최신으로 사용하지 않은 이유는 Annotation Processor와 Get, Set Prefix로 인해 컴파일 시점에서 객체 간의 매핑 실패를
해결하기 위해서입니다. 롬복 최신 버전을 사용하는 경우 <br/>

```groovy
implementation 'org.projectlombok:lombok-mapstruct-binding:0.2.0' 
```

해당 의존성을 사용하셔야 합니다.
