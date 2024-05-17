- SDK от Google для создания кроссплатформенных приложений.
- На данный момент всё ещё поддерживается компанией Google и имеет высокую поддержку со стороны сообщества.
- Для данного инструмента имеется библиотека [dart_serial](https://github.com/xclud/dart_serial/).
- В ходе эксперимента с ним было выявлено два существенных недостатка:
	- Высокая задержка получения и отправки данных, так как задержка составила 250 милисекунд.
	- Периодически входящие и исходящие данные на клиентском устройстве были испорчены.