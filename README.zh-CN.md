# Codicefun Blog

一个基于 Next.js 全栈的企业编辑部式博客平台，面向组织、团队和产品，支持多作者投稿 + 编辑审核发布的工作流——类似 JetBrains Blog：单站点、多作者、由编辑把关发布内容。

> English version: [README.md](README.md)

## 功能

**当前**

- Next.js 16（App Router）+ React 19 + TypeScript
- Tailwind CSS 4
- pnpm 包管理

**规划中**

- 编辑部工作流：草稿 → 提交 → 审核 → 发布（支持定时发布）
- 角色权限：管理员 / 编辑 / 作者 / 读者
- 产品线分类、标签、作者页、时间归档
- Markdown 写作与代码高亮
- SEO：Metadata、sitemap、RSS
- 国际化：默认英文 UI，支持 i18n
- Newsletter 邮件订阅

## 技术栈

| 分层 | 选型 |
| --- | --- |
| 框架 | Next.js 16（App Router） |
| UI | React 19 + Tailwind CSS 4 |
| 语言 | TypeScript |
| 包管理 | pnpm |

## 快速开始

### 环境要求

- Node.js 20+（建议 LTS）
- pnpm

### 安装与运行

```bash
pnpm install
pnpm dev
```

浏览器打开 [http://localhost:3000](http://localhost:3000) 查看效果。

### 常用命令

| 命令 | 说明 |
| --- | --- |
| `pnpm dev` | 启动开发服务器 |
| `pnpm build` | 生产构建 |
| `pnpm start` | 运行生产构建 |
| `pnpm lint` | 运行 ESLint |

## 项目结构

```
src/
└─ app/
   ├─ layout.tsx   # 根布局，所有页面共享
   ├─ page.tsx     # 首页
   └─ globals.css  # 全局样式与 Tailwind 配置
```

## 开发路线

1. 认证与角色权限（Better Auth）
2. 文章写作与编辑部工作流
3. 前台页面：文章列表、详情、分类、标签、作者页
4. 管理后台与内容管理
5. 国际化（默认英文，中文为辅）
6. Newsletter、搜索、测试与部署

## 许可证

[MIT](LICENSE)
