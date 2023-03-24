import requests
import json
import os
import re
# import xlwt
import time

# 图片类型
img_prefix = ['jpg', 'png', 'jpeg']
# 存取的图片名称
img_array = []


# 读取文件夹中所有的图片
def read_img():
    files = os.listdir('D:\\Pycharm\\list')
    for file in files:
        index = file.find('.')
        prefix = file[index + 1:]
        if prefix in img_prefix:
            img_array.append(file)


# 将获得的字段进行分割，存储在每个独立的f_result中
def sub_result1(msg_result):
    print("sub_result1")
    sub1 = re.sub(r'"', '#', ",".join(msg_result))
    sub1 = re.sub(r'\\n', '/', sub1)
    print(sub1)
    money = re.findall(r'#value#:#(.+?)#,#key#:#money', sub1)
    print('money:' + money[0])
    time = re.findall(r'金额#},{#value#:#(.+?)#,#key#:#date', sub1)
    print('time:' + time[0])
    shop = re.findall(r'单号#},{#value#:#(.+?)#,#key#:#shop', sub1)
    print('shop:' + shop[0])
    value = re.findall(r'商户编号#},{#value#:#(.+?)#,#key#:#sku', sub1)
    print('value:' + value[0])
    bill = '小票'
    main_type = 'expend'
    f_result = [time[0], shop[0], main_type, bill, money[0], value[0]]
    print(f_result)
    # write_to_all(f_result)


# 存取每个信息的总信息列表

all_list = []


# 将每个独立的数据信息写入一个总的数据列表中
def write_to_all(f_result):
    print("write_to_all")
    all_list.append(f_result)

    print(all_list)
    print("-------------------------------------------------")


# 将读取到的字段信息写入excel表格中
def write_excel():
    print("write_into_excel")
    file = xlwt.Workbook('encoding = utf-8')
    sheet1 = file.add_sheet('sheet1', cell_overwrite_ok=True)
    # sheet1.write(a,b,c) 函数中参数a、b、c分别对应行数、列数、单元格内容
    sheet1.write(0, 0, "日期")  # 第1行第1列
    sheet1.write(0, 1, "商户")  # 第1行第2列
    sheet1.write(0, 2, "收支类型")  # 第1行第3列
    sheet1.write(0, 3, "票据类型")  # 第1行第3列
    sheet1.write(0, 4, "金额")  # 第1行第3列
    sheet1.write(0, 5, "内容")
    sheet1.write(0, 6, "备注")
    # 循环填入数据
    test_write_list = all_list
    j = 0
    i = 0
    for i in range(len(test_write_list)):  # 查看总共的all_list中有几条数据信息
        temp_list = test_write_list[i]  # 将当前的数据信息暂存
        for j in range(len(temp_list)):
            sheet1.write(i + 1, j, temp_list[j])
    file.save('E:\\output\\0\\TEST.xls')
    print("end")


# 读取图片路径
def get_file_content(filePath):
    with open(filePath, 'rb') as fp:
        return fp.read()


# 调用接口，获取返回结果
class CommonOcr(object):
    def __init__(self, img_path):
        self._app_id = 'XXX'
        self._secret_code = 'XXX'
        self._img_path = 'D:\\Pycharm\\list\\'

    def recognize(self):
        url = 'XXXX'
        head = {}
        # 用于存储返回的字段结果
        msg_result = []
        try:
            for test_img in img_array:
                image = get_file_content(self._img_path + test_img)
                print(test_img)
                head['x-ti-app-id'] = self._app_id
                head['x-ti-secret-code'] = self._secret_code
                result = requests.post(url, data=image, headers=head)
                msg_result.append(result.text)
                sub_result1(msg_result)
                msg_result = []
                time.sleep(1)
            write_excel()
        except Exception as e:
            return e


# main function
if __name__ == "__main__":
    read_img()
    response = CommonOcr(r'')
    print(response.recognize())
    # write_excel()

