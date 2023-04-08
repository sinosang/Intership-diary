import time
import requests
import re

import xlwt

if __name__ == "__main__":
    nlen = 40
    all_list = []
    for num in range(nlen):
        num = num+2
        # print(num)
        # url = 'XXXX'+str(num)+'&q=零食&equipmentCode=&searchMethod=original&spm=d3d3Lnlpd3Vnby5jb20v.d3d3Lnlpd3Vnby5jb20vc2VhcmNoL3MuaHRtbD9jcGFnZT0yJnE9JUU2JTlDJThEJUU4JUEzJTg1JnNlYXJjaE1ldGhvZD1vcmlnaW5hbA%3D%3D.d3d3Lnlpd3Vnby5jb20vc2VhcmNoL3MuaHRtbD9jcGFnZT0yJnE9JUU4JUExJUEzJUU2JTlDJThEJnNlYXJjaE1ldGhvZD1vcmlnaW5hbA%3D%3D.d3d3Lnlpd3Vnby5jb20vc2VhcmNoL3MuaHRtbD9jcGFnZT0yJnE9JUU3JTk5JUJFJUU4JUI0JUE3JnNlYXJjaE1ldGhvZD1vcmlnaW5hbA%3D%3D'
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'}
        print(url)
        response = requests.get(url=url, headers=headers)
        response.encoding = "utf-8"   #设置编码为utf-8
        # print(response.encoding)  #判断编码情况
        page_text = response.text
        ex = '<a class="productloc" href=".*?" target="_blank" title="(.*?)">.*?</a>'
        hosp_list = re.findall(ex, page_text, re.S)
        all_list.append(hosp_list)
        time.sleep(1)
        # print(page_text)
    print(all_list)

    file = xlwt.Workbook('encoding = utf-8')
    sheet1 = file.add_sheet('sheet1', cell_overwrite_ok=True)
    # sheet1.write(a,b,c) 函数中参数a、b、c分别对应行数、列数、单元格内容
    sheet1.write(0, 0, "药品名称")  # 第1行第1列
    # 循环填入数据
    j = 0
    i = 0
    # print(range(len(all_list)))
    for i in range(len(all_list)):  # 查看总共的all_list中有几条数据信息
        temp_list = all_list[i]  # 将当前的数据信息暂存
        print(len(temp_list))  #查看当前列有多少数据
        for j in range(len(temp_list)):
            sheet1.write(j + i * 120, 0, temp_list[j])
    file.save('E:\\FFOutput\\cook2.xls')
    print("end")