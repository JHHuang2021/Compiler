#! /usr/bin/python3
import os
import sys
import time


def copy_file(source, target):
    os.system('cp {} {} -f'.format(source, target))


result_filename = 'result.out'
stdout_filename = 'stdout.out'
stderr_filename = 'stderr.out'
os.chdir(sys.path[0])
testcases_base_dir = 'sema/'
testcases = [
    input[2:-1] for input in open(testcases_base_dir+'judgelist.txt', 'r').readlines()]
with open(result_filename, 'w'):
    pass
testcase_number = len(testcases)
for i, testcase in enumerate(testcases):
    input_path = testcases_base_dir+testcase
    input_prog = open(input_path, 'r').read()
    # if input_prog.count('Verdict: Success'):
    copy_file(input_path, 'test.mx')
    cmd = 'java -XX:+ShowCodeDetailsInExceptionMessages @/tmp/cp_q75i02nztlhwf165oppqqhjp.argfile Main '
    print("testing case:{} ({}/{})".format(testcase, i, testcase_number))
    if (os.system(cmd + "> test.out") >> 8):
        std_ret = input_prog.count('Verdict: Fail')
        stdout_ctx = open("test.out", 'r').read()
        with open(result_filename, 'a') as result_file:
            result_file.write(
                'Case : {} Error\t\n'.format(testcase))
            result_file.write(
                '<stdout:>\n{}<End of stdout>\n\n'.format(stdout_ctx))
