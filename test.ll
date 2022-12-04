%class.__built_in_array_int1 = type { i32, i32* }
@str_0 = private unnamed_addr constant [1 x i8] c"\00"
@var_def_0 = global i32 zeroinitializer
@var_def_1 = global %class.__built_in_array_int1 zeroinitializer
@var_def_7 = global i32 zeroinitializer
declare void @__builtin_print(i8* %s_0)
declare void @__builtin_println(i8* %s_0)
declare void @__builtin_printInt(i32 %int_0)
declare void @__builtin_printlnInt(i32 %int_0)
declare i32 @__builtin_getInt()
declare i8* @__builtin_getString()
declare i8* @__builtin_toString(i32 %int_0)
declare i32* @__builtin_malloc(i32 %size_0)
declare i8* @__builtin_str_add(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_eq(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_ne(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_sgt(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_sge(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_slt(i8* %lhs_0, i8* %rhs_1)
declare i1 @__builtin_str_sle(i8* %lhs_0, i8* %rhs_1)
declare i32 @__builtin_length(i8* %this_0)
declare i8* @__builtin_substring(i8* %this_0, i32 %left_1, i32 %right_2)
declare i32 @__builtin_parseInt(i8* %this_0)
declare i32 @__builtin_ord(i8* %this_0, i32 %pos_1)
define i32 @jud(i32 %arg_define_8)
{
jud_0:
	%var_def_9 = alloca i32
	%var_def_10 = alloca i32
	store i32 0, i32* %var_def_9
	br label %jud_2
jud_1:
	%load_reg_17 = load i32, i32* @var_def_0
	%binary_expr_16 = sdiv i32 %load_reg_17, %arg_define_8
	%load_reg_18 = load i32, i32* %var_def_9
	%cmp_13 = icmp slt i32 %load_reg_18, %binary_expr_16
	br i1 %cmp_13, label %jud_2, label %jud_4
jud_2:
	%var_def_22 = alloca i1
	store i1 0, i1* %var_def_22
	store i32 0, i32* %var_def_10
	br label %jud_6
jud_3:
	%unary_20 = load i32, i32* %var_def_9
	%tmp_21 = add i32 %unary_20, 1
	store i32 %tmp_21, i32* %var_def_9
	br label %jud_1
jud_4:
	ret i32 0
jud_5:
	%binary_expr_27 = sub i32 %arg_define_8, 1
	%load_reg_28 = load i32, i32* %var_def_10
	%cmp_25 = icmp slt i32 %load_reg_28, %binary_expr_27
	br i1 %cmp_25, label %jud_6, label %jud_8
jud_6:
	%load_reg_33 = load i32, i32* %var_def_9
	call void @__builtin_printInt(i32 %load_reg_33)
	call void @__builtin_printInt(i32 %arg_define_8)
	%load_reg_36 = load i32, i32* %var_def_10
	call void @__builtin_printInt(i32 %load_reg_36)
	%load_reg_37 = getelementptr inbounds [1 x i8], [1 x i8]* @str_0, i32 0, i32 0
	call void @__builtin_println(i8* %load_reg_37)
	%load_reg_42 = load i32, i32* %var_def_9
	%binary_expr_41 = mul i32 %load_reg_42, %arg_define_8
	%load_reg_45 = load i32, i32* %var_def_10
	%binary_expr_44 = add i32 %binary_expr_41, %load_reg_45
	%get_element_46 = getelementptr inbounds %class.__built_in_array_int1, %class.__built_in_array_int1* @var_def_1, i32 0, i32 1
	%load_reg_48 = load i32*, i32** %get_element_46
	%expr_array_47 = getelementptr inbounds i32, i32* %load_reg_48, i32 %binary_expr_44
	%load_reg_54 = load i32, i32* %var_def_9
	%binary_expr_53 = mul i32 %load_reg_54, %arg_define_8
	%load_reg_57 = load i32, i32* %var_def_10
	%binary_expr_56 = add i32 %binary_expr_53, %load_reg_57
	%binary_expr_58 = add i32 %binary_expr_56, 1
	%get_element_59 = getelementptr inbounds %class.__built_in_array_int1, %class.__built_in_array_int1* @var_def_1, i32 0, i32 1
	%load_reg_61 = load i32*, i32** %get_element_59
	%expr_array_60 = getelementptr inbounds i32, i32* %load_reg_61, i32 %binary_expr_58
	%load_reg_62 = load i32, i32* %expr_array_47
	%load_reg_63 = load i32, i32* %expr_array_60
	%cmp_49 = icmp sgt i32 %load_reg_62, %load_reg_63
	br i1 %cmp_49, label %jud_9, label %jud_10
jud_7:
	%unary_30 = load i32, i32* %var_def_10
	%tmp_31 = add i32 %unary_30, 1
	store i32 %tmp_31, i32* %var_def_10
	br label %jud_5
jud_8:
	%load_reg_67 = load i1, i1* %var_def_22
	%not_66 = xor i1 1, %load_reg_67
	br i1 %not_66, label %jud_12, label %jud_13
jud_9:
	store i1 1, i1* %var_def_22
	br label %jud_11
jud_10:
	br label %jud_11
jud_11:
	br label %jud_7
jud_12:
	ret i32 1
jud_13:
	br label %jud_14
jud_14:
	br label %jud_3
jud_15:
	br label %jud_14
}
define i32 @main()
{
main_0:
	call void @__global_var_init()
	%getInt_68 = call i32 @__builtin_getInt()
	store i32 %getInt_68, i32* @var_def_0
	store i32 0, i32* @var_def_7
	br label %main_2
main_1:
	%load_reg_74 = load i32, i32* @var_def_7
	%load_reg_75 = load i32, i32* @var_def_0
	%cmp_72 = icmp slt i32 %load_reg_74, %load_reg_75
	br i1 %cmp_72, label %main_2, label %main_4
main_2:
	%getInt_79 = call i32 @__builtin_getInt()
	%get_element_82 = getelementptr inbounds %class.__built_in_array_int1, %class.__built_in_array_int1* @var_def_1, i32 0, i32 1
	%load_reg_84 = load i32*, i32** %get_element_82
	%load_reg_85 = load i32, i32* @var_def_7
	%expr_array_83 = getelementptr inbounds i32, i32* %load_reg_84, i32 %load_reg_85
	store i32 %getInt_79, i32* %expr_array_83
	br label %main_3
main_3:
	%unary_77 = load i32, i32* @var_def_7
	%tmp_78 = add i32 %unary_77, 1
	store i32 %tmp_78, i32* @var_def_7
	br label %main_1
main_4:
	%load_reg_88 = load i32, i32* @var_def_0
	store i32 %load_reg_88, i32* @var_def_7
	br label %main_6
main_5:
	%load_reg_91 = load i32, i32* @var_def_7
	%cmp_90 = icmp sgt i32 %load_reg_91, 0
	br i1 %cmp_90, label %main_6, label %main_8
main_6:
	%load_reg_98 = load i32, i32* @var_def_7
	%jud_96 = call i32 @jud(i32 %load_reg_98)
	%cmp_99 = icmp sgt i32 %jud_96, 0
	br i1 %cmp_99, label %main_9, label %main_10
main_7:
	%load_reg_94 = load i32, i32* @var_def_7
	%binary_expr_93 = sdiv i32 %load_reg_94, 2
	store i32 %binary_expr_93, i32* @var_def_7
	br label %main_5
main_8:
	ret i32 0
main_9:
	%load_reg_102 = load i32, i32* @var_def_7
	%toString_100 = call i8* @__builtin_toString(i32 %load_reg_102)
	call void @__builtin_print(i8* %toString_100)
	ret i32 0
main_10:
	br label %main_11
main_11:
	br label %main_7
main_12:
	br label %main_11
}
define void @__global_var_init() section ".text.startup"
{
__global_var_init_0:
	%class_load_2 = getelementptr inbounds %class.__built_in_array_int1, %class.__built_in_array_int1* @var_def_1, i32 0, i32 0
	%class_load_3 = getelementptr inbounds %class.__built_in_array_int1, %class.__built_in_array_int1* @var_def_1, i32 0, i32 1
	store i32 20, i32* %class_load_2
	%load_reg_5 = load i32, i32* %class_load_2
	%malloc_size_4 = mul i32 %load_reg_5, 8
	%malloc_reg_6 = call i32* @__builtin_malloc(i32 %malloc_size_4)
	store i32* %malloc_reg_6, i32** %class_load_3
	ret void
}
