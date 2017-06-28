#java的安装目录
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-3.b12.el7_3.x86_64
#运行程序所使用的用户
OWNER=root
#Java程序的目录
APP_HOME=/home/java
#Main方法的类
APP_MAINCLASS=Server
#日志文件
LOG_FILE=$APP_HOME/log.file
#设置CLASSPATH
CLASSPATH=$CLASSPATH:$APP_HOME/Server.jar


#设置运行参数
JAVA_OPTS="-Xms512m -Xmx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m"

#echo $CLASSPATH;
psid=0

#检查Java程序是否运行
checkpid(){
    javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAINCLASS`

    if [ -n "$javaps" ];then
        psid=`echo $javaps | awk '{print $1}'`
    else
        psid=0
    fi
}

#运行程序
start(){
    checkpid

    if [ $psid -ne 0 ];then
        echo "WARN:$APP_MAINCLASS already started!(pid=$psid)"
    else
        echo "Starting $APP_MAINCLASS..."
        JAVA_CMD="nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAINCLASS >> $LOG_FILE 2>&1 &"
        su - $OWNER -c "$JAVA_CMD"
        checkpid
        if [ $psid -ne 0 ];then
            echo "Started $APP_MAINCLASS (pid=$psid)[OK]"
        else
            echo "Started $APP_MAINCLASS [FAILED]"
        fi
    fi
}

#停止程序
stop(){
    checkpid
    if [ $psid -ne 0 ];then
        echo "Stoping $APP_MAINCLASS...(pid=$psid)"
        su - $OWNER -c "kill $psid"

        checkpid
        if [ $psid -ne 0 ];then
            echo "Stoping use kill -9"
            su - $OWNER -c "kill -9 $psid"
        fi

        checkpid
        if [ $psid -eq 0 ];then
            echo "Stoped $APP_MAINCLASS [OK]"
        else
            echo "Stoped $APP_MAINCLASS [Failed]"
            stop
        fi

    else
        echo "WARN:$APP_MAINCLASS is not runing"
    fi
}

#查看状态
status(){
    checkpid

    if [ $psid -ne 0 ];then
        echo "$APP_MAINCLASS is runing (pid=$psid)"
    else
        echo "$APP_MAINCLASS is not runing"
    fi
}

#帮助信息
info() {
    echo "System Information:"
    echo "****************************"
    echo `head -n 1 /etc/issue`
    echo `uname -a`
    echo
    echo "JAVA_HOME=$JAVA_HOME"
    echo `$JAVA_HOME/bin/java -version`
    echo
    echo "APP_HOME=$APP_HOME"
    echo "APP_MAINCLASS=$APP_MAINCLASS"
    echo "****************************"
}

#$1表示接收第一个参数，如 ./run.sh start 。则$1就是start 
case "$1" in
    'start')
        start
        ;;
    'stop')
        stop
        ;;
    'restart')
        stop
        start
        ;;
    'info')
        info
        ;;
    'status')
        status
        ;;
    *)
    echo "Usage: $0 {start|stop|restart|status|info}"
    exit 1
esac
exit 0;
