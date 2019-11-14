#!/usr/bin/env python
from py4j.java_gateway import JavaGateway, GatewayParameters
import py4j

def f(object, fld):
    return py4j.java_gateway.get_field(object, fld)

def analyse():
    # gateway = JavaGateway(gateway_parameters=GatewayParameters(auto_field=True))    
    gateway = JavaGateway(gateway_parameters=GatewayParameters(address='localhost', port=25333))
    analyst=gateway.entry_point.getTimeAnalyst()
    normalizer=analyst.getNormalizer()
    normalizer.parse("Hi，all.下周一下午三点开会")
    units = normalizer.getTimeUnit()
    for unit in units:
        print(unit.getTime())

    DateUtil=gateway.jvm.com.time.util.DateUtil()
    normalizer.parse("周五下午7点到8点", "2017-07-19-00-00-00")
    units = normalizer.getTimeUnit()
    print("周五下午7点到8点")
    for unit in units:
        expr=f(unit, "Time_Expression")
        print("时间文本:"+ expr +",对应时间:"+ DateUtil.formatDateDefault(unit.getTime()))

if __name__ == '__main__':
    analyse()

