# DreamingfishCore

DreamingfishCore 是为梦鱼服（DreamingFish）迁移整理的 NeoForge 1.21.1 核心模组。

## 保留模块

- RPG 属性系统：勇气、感染、体力、肢体伤害、复活点数与身份转换
- 等级系统：击杀、探索、成就奖励与玩家等级数据
- NPC 系统：对话、关系、想法与警告规则
- 任务与剧情系统：故事阶段、玩家任务、故事碎片与随记本
- 蓝图系统：配方学习与合成限制
- 服务器基础 UI：加载界面、提示、服务器信息 HUD
- 自定义物品、实体、声音、附魔与数据生成

## 已剥离模块

- 经济系统
- 领地系统
- 真实时间同步
- GTA 三段式开场
- 商店、市场、红包、收货箱等整套交易系统

## 技术栈

- Minecraft 1.21.1
- NeoForge
- Java 21
- GeckoLib
- Gson

## 构建

```bash
./gradlew build
./gradlew runClient
./gradlew runServer
```

## 协议

GNU Affero General Public License v3.0
