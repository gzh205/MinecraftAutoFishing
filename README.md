# Minecraft AutoFishing
## 简介
这是一个用于《我的世界》游戏的拓展组件，能够为玩家提供夜视效果和自动钓鱼的功能
## 使用方式
在游戏启动后，按下H键即可打开设置界面，在设置界面中有2个复选框：
1.勾选"enable auto fishing"开启自动钓鱼功能
2.勾选"enable night vision"开启夜视的功能
## 原理
1. 自动钓鱼的功能通过一个@SubscribeEvent监听鱼儿上钩的声音，并检查Minecraft.FishingBobberEntity中的状态是否为BOBBING，如果是的话则收回鱼钩并重新抛出鱼钩。  
2. 夜视功能则是设置Minecraft.GameSettings.gamma的值，通过将该值设置为255获得夜视效果。
3. 由于部分库函数是私有成员，因此只能通过反射的方式强制调用。
## 更新内容
从1.14.3更新到1.16.1，其中：
1. CheckBox和CheckNightVision的onPress方法更名为func_230930_b_  
2. CheckboxButton构造函数的第三个参数从String变为ITextComponent
3. CheckboxButton新增了isChecked方法(但是并未使用)  