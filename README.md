# ik-activiti
activiti study 

## 流程记录的设计

### 什么是流程记录
* 流程中，每发生一个重要事件，都需要有一条信息记录，其类似activity的概念，但又比后者更宽泛。
* 包括：用户任务(UserTask)的执行记录，自动任务的执行记录，沟通，转办，驳回，会签等业务动作的记录等等

## 转办
* 用spring aop来实现
* 通用接口实现代审查询
* 拦截接口方法，审查当前用户是否有代理任务，有则将被代理人的代审纪录一并返回。

## 关于沟通的实现
沟通的需求为：
* 在流程任何人工步骤中，都可以找若干个（一个或多个）人员进行沟通，让他们给出意见。
* 沟通发起人可以提供一条沟通事由
* 沟通人员可以为流程提供 反馈信息和附件
* 沟通不必作为主流程的过程必要条件，即使发出了沟通，不需要等所有人沟通回复后，才能继续。
沟通发起人随时可以继续流程，忽略之后的沟通回复。

暂时考虑的实现方式为:<p>
* 因为沟通人数不定，抽象为每向一个人沟通为一个子流程，可以并发多个子流程来达到向多人沟通的目的。
* 被沟通的人还可以再沟通给其他人。
* 沟通记录为：开始沟通时，记录 沟通人开始沟通。查看沟通详情（链接），点击后进入沟通详情页面。
* 沟通有时效性，沟通发起人可以在发起一个沟通后，任意时间，结束沟通，则流程记录中多记录一条数据，标识沟通结束。
* 沟通中，被沟通人可以看到流程信息，可以回复沟通；一旦沟通结束，被沟通人可以查看流程信息，沟通记录，但不可以再回复沟通。

## 流程撤销
* 考虑使用事件机制。
* 发起者可以申请撤销。系统发给该流程实例一个撤销申请事件。流程捕捉该事件，来处理撤销校验。
* 通过校验，则系统给该流程实例一个撤销批准事件。流程捕捉后，正式撤销。
* 不通过校验，则系统给发起者一个撤销失败的信息。

## 关于会签 
可以考虑用Multi-instance来实现。以下为示例代码:
```xml
<userTask id="miTasks" name="My Task" activiti:assignee="${assignee}">
  <multiInstanceLoopCharacteristics isSequential="false"
     activiti:collection="assigneeList" activiti:elementVariable="assignee" >
    <completionCondition>${nrOfCompletedInstances/nrOfInstances >= 0.6 }</completionCondition>
  </multiInstanceLoopCharacteristics>
</userTask>
```

